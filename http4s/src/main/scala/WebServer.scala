import cats.effect.{Async, Resource}
import fs2.Stream
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import scala.annotation.unused

object WebServer {

  def stream[F[_]: Async]: Stream[F, Nothing] = {
    val messengerAlg = Messenger.impl[F]
    val httpApp = Routes.myRoutes[F](messengerAlg).orNotFound
    for {
      exitCode <-
        BlazeServerBuilder[F](scala.concurrent.ExecutionContext.global)
          .bindHttp(8080, "0.0.0.0")
          .withHttpApp(httpApp)
          .serve
    } yield exitCode
  }.drain
}
