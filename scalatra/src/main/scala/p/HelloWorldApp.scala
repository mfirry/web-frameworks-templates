package p
import org.scalatra._
import org.scalatra.json._

class HelloWorldApp extends ScalatraServlet with JacksonJsonSupport {
  import HelloWorldApp._

  import org.json4s.{DefaultFormats, Formats}

  implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    List(1, 2, 3)
  }

  get("/json") {
    Message("Hello, World!")
  }

  get("/say-hi/   :whom") {
    Message(s"""Hello, ${params("whom")} !""")
  }

}

object HelloWorldApp {
  final case class Message(message: String)
}
