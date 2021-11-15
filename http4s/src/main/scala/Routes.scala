import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import scala.annotation.unused

object Routes {

  import io.circe.syntax._
  import org.http4s.circe._

  val s = "Ciao"

  @SuppressWarnings(Array("all"))
  def myRoutes[F[_]: Sync](@unused M: Messenger[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root =>
        for {
          resp <- Ok((List(1, 2, 3)).asJson)
        } yield resp

      case GET -> Root / "plaintext" =>
        for {
          resp <- Ok("Hello, World!")
        } yield resp

      case GET -> Root / "json" =>
        for {
          resp <- Ok(Messenger.Message("Hello, World!").asJson)
        } yield resp

    }
  }
}
