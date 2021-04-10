import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {
  def run(args: List[String]) =
    WebServer.stream[IO].compile.drain.as(ExitCode.Success)
}
