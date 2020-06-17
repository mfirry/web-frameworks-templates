import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe._

trait Messenger[F[_]] {
  def message(m: Messenger.Message): F[Messenger.Message]
}

object Messenger {
  implicit def apply[F[_]](implicit ev: Messenger[F]): Messenger[F] = ev

  final case class Message(message: String) extends AnyVal

  object Message {
    implicit val messageEncoder: Encoder[Message] = new Encoder[Message] {
      final def apply(m: Message): Json =
        Json.obj(("message", Json.fromString(m.message)))
    }
  }

  def impl[F[_]: Applicative]: Messenger[F] =
    new Messenger[F] {
      def message(m: Message): F[Message] = Message(m.message).pure[F]
    }

}
