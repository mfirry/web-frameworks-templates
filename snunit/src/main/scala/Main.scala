import snunit._
import upickle.default._

case class Message(message: String)

object Main extends App {
  implicit val messageRW = macroRW[Message]
  val server = SyncServerBuilder()
    .withRequestHandler(_.withPath("/") {
      _.send(StatusCode.OK, write(List(1, 2, 3)), Seq("Content-type" -> "application/json"))
    })
    .withRequestHandler(_.withPath("/json") {
      _.send(StatusCode.OK, write(Message("Hello, World!")), Seq("Content-type" -> "application/json"))
    })
    .withRequestHandler(_.withPath("/plaintext") {
      _.send(StatusCode.OK, "Hello, World!", Seq("Content-type" -> "text/plain"))
    })
    .withRequestHandler(_.send(StatusCode.NotFound, "Not found", Seq.empty))
    .build()

  server.listen()
}
