import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}

// The algebra: defines *what* the Messenger can do without specifying *how* or
// which effect type F is. F[_] is the abstract effect (e.g. IO, Task), making
// the logic easy to test by substituting F with something simpler (e.g. Id).
trait Messenger[F[_]] {
  def message(m: Messenger.Message): F[Messenger.Message]
}

object Messenger {
  // Summoner: lets you call Messenger[F] to retrieve a given instance in scope,
  // e.g. Messenger[IO] instead of summon[Messenger[IO]].
  def apply[F[_]](using ev: Messenger[F]): Messenger[F] = ev

  final case class Message(message: String) extends AnyVal

  object Message {
    given Encoder[Message] with
      def apply(m: Message): Json =
        Json.obj(("message", Json.fromString(m.message)))
  }

  // The interpreter: the concrete implementation of the algebra for any F that has
  // an Applicative (the minimal requirement to lift a pure value into F via .pure).
  // This is where the "how" lives — kept separate from the "what" defined in the trait.
  def impl[F[_]: Applicative]: Messenger[F] =
    new Messenger[F] {
      def message(m: Message): F[Message] = Message(m.message).pure[F]
    }

}
