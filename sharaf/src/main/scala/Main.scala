import io.undertow.Undertow
import ba.sake.tupson.JsonRW
import ba.sake.sharaf.*, routing.*

case class Message(message: String) derives JsonRW

val routes = Routes:
  case GET() -> Path() =>
    Response.withBody(Seq(1, 2, 3))
  case GET() -> Path("json") =>
    Response.withBody(Message("Hello World!"))
  case GET() -> Path("plaintext") =>
    Response.withBody("Hello World!")

@main def sharafMain = 
  Undertow
    .builder
    .addHttpListener(8080, "localhost")
    .setHandler(SharafHandler(routes))
    .build
    .start()
