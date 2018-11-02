import cats.effect._
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.{ExitCode, StreamApp}

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

object WebServerExample extends WebServerApp[IO]

case class Message(message: String)

class WebServerApp[F[_] : Effect] extends StreamApp[F] {
  import fs2._
  override def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, ExitCode] =
    Scheduler(corePoolSize = 2).flatMap { implicit scheduler =>
      BlazeBuilder[F].bindHttp(9000)
        .mountService(new ExampleService[F].service, "/")
        .serve
    }
}

class ExampleService[F[_]](implicit F: Effect[F]) extends Http4sDsl[F] {

  object WhomQueryParamMatcher extends QueryParamDecoderMatcher[String]("whom")

  def service(implicit scheduler: fs2.Scheduler,
      executionContext: ExecutionContext = ExecutionContext.global): HttpService[F] =
    Router[F](
      "" -> HttpService[F] {
        case GET -> Root =>
          import io.circe.syntax._
          import org.http4s.circe._
          Ok((List(1, 2, 3)).asJson)

        case GET -> Root / json =>
          import io.circe.syntax._
          import io.circe.generic.auto._
          import org.http4s.circe._
          Ok(Message("Hello, World!").asJson)

        case GET -> Root / plaintext =>
          Ok("Hello, World!")
        case GET -> Root / sayHi :? WhomQueryParamMatcher(whom) =>
          Ok(s"Hello, ${whom}!")
      }
    )

}

