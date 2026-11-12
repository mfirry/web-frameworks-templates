import ba.sake.sharaf.*
import ba.sake.sharaf.undertow.UndertowSharafServer
import ba.sake.tupson.JsonRW

case class Message(message: String) derives JsonRW

object Main {
  def main(args: Array[String]): Unit = {
    val routes = Routes {
      case GET -> Path() => Response.withBody(List(1, 2, 3))
      case GET -> Path("json") => Response.withBody(Message("Hello, World!"))
      case GET -> Path("plaintext") => Response.withBody("Hello, World!")
    }
    UndertowSharafServer("localhost", 8080, routes).start()
  }
}
