import zio._
import zio.http._

object HelloWorld extends ZIOAppDefault {

  val routes = Routes(
    Method.GET / trailing -> handler(Response.text(List(1, 2, 3).toString)),
    Method.GET / "plaintext" -> handler(Response.text("Hello, World!"))
  )

  override def run =
    Server.serve(routes).provide(Server.defaultWithPort(8090))

}
