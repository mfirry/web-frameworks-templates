import zio._
import zio.http._

object HelloWorld extends ZIOAppDefault {

  val app: HttpApp[Any] = Routes(
    Method.GET / trailing -> handler(Response.text(List(1, 2, 3).toString)),
    Method.GET / "plaintext" -> handler(Response.text("Hello, World!"))
  ).toHttpApp

  override def run =
    Server.serve(app).provide(Server.defaultWithPort(8090))

}
