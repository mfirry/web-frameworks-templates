import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp.Simple {
  val run = WebServer.run[IO]

}
