import cats.effect.{ExitCode, IO, IOApp}
import com.comcast.ip4s._
import org.http4s.implicits._
import org.http4s.ember.server.EmberServerBuilder

object Main extends IOApp.Simple {
  def run =
    EmberServerBuilder
      .default[IO]
      .withHost(host"0.0.0.0")
      .withPort(port"8080")
      .withHttp2
      .withHttpApp(Routes[IO].routes.orNotFound)
      .build
      .useForever
}
