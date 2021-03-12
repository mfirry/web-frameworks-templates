import cats.effect.{ConcurrentEffect, Timer, ContextShift}
import cats.effect.ContextShift
import fs2.Stream
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._

object WebServer {

  def stream[F[_]: ConcurrentEffect](implicit
      T: Timer[F],
      C: ContextShift[F]
  ): Stream[F, Nothing] = {
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
