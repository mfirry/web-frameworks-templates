import cats.effect._
import fs2._
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.{ExitCode, StreamApp}
import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext

object WebServerExample extends WebServerApp[IO]

class WebServerApp[F[_] : Effect] extends StreamApp[F] {
  override def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, ExitCode] =
    Scheduler(corePoolSize = 2).flatMap { implicit scheduler =>
      BlazeBuilder[F].bindHttp(8080)
        .mountService(new ExampleService[F].service, "/")
        .serve
    }
}

class ExampleService[F[_]](implicit F: Effect[F]) extends Http4sDsl[F] {
  def service(
               implicit scheduler: Scheduler,
               executionContext: ExecutionContext = ExecutionContext.global): HttpService[F] =
    Router[F](
      "" -> HttpService[F] {
        case GET -> Root =>
          Ok((List(1, 2, 3)).asJson)
        case GET -> Root / str =>
          Ok(str.asJson)
      }
    )

}

