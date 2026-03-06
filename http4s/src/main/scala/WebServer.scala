import cats.effect.{Async, Resource}
import fs2.Stream
import org.http4s.ember.server.EmberServerBuilder
import fs2.io.net.Network
import org.http4s.implicits._
import com.comcast.ip4s._
import scala.annotation.unused

object WebServer {

  def run[F[_]: Async: Network]: F[Nothing] = {
    // Messenger.impl[F] creates a concrete implementation of the Messenger[F] algebra —
    // an interface whose effects are abstract over F. This follows the tagless final pattern,
    // where business logic is expressed as a trait (Messenger[F]) and wired to a real
    // implementation here at the edge, once we know F (e.g. IO).
    val messengerAlg = Messenger.impl[F]
    // myRoutes defines all HTTP endpoints and depends on the Messenger algebra to handle
    // business logic. Passing messengerAlg here is the dependency injection step.
    // .orNotFound turns the partial HttpRoutes[F] into a total HttpApp[F] that returns
    // 404 for any unmatched requests.
    val httpApp = Routes.myRoutes[F](messengerAlg).orNotFound
    for {
      exitCode <-
        EmberServerBuilder
          .default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(httpApp)
          .build
    } yield ()
  }.useForever
}
