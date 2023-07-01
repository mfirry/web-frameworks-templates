import cats.Monad
import cats.syntax.all._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import io.circe.syntax._
import io.circe.{Encoder, Json}
import org.http4s.circe._

final case class Message(message: String)

object Message {
  implicit val messageEncoder: Encoder[Message] = (m: Message) =>
    Json.obj(("message", Json.fromString(m.message)))
}

class Routes[F[_]: Monad] extends Http4sDsl[F] {
  def routes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case GET -> Root               => Ok(List(1, 2, 3).asJson)
      case GET -> Root / "plaintext" => Ok("Hello, World!")
      case GET -> Root / "json"      => Ok(Message("Hello, World!").asJson)
    }
}

object Routes {
  def apply[F[_]: Monad]: Routes[F] = new Routes[F]
}
