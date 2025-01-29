import cats.effect.{Async, Resource}
import fs2.Stream
import org.http4s.ember.server.EmberServerBuilder
import fs2.io.net.Network
import org.http4s.implicits._
import com.comcast.ip4s._
import scala.annotation.unused

object WebServer {

  def run[F[_]: Async: Network]: F[Nothing] = {
    val messengerAlg = Messenger.impl[F]
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
