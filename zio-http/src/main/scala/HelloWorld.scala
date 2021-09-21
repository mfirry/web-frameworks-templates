import zhttp.http._
import zhttp.service.Server
import zio._

object HelloWorld extends App {

  val app = Http.collect[Request] {
    case Method.GET -> Root => Response.text(List(1,2,3).toString)
    case Method.GET -> Root / "plaintext" => Response.text("Hello, World!")
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    Server.start(8090, app).exitCode

}