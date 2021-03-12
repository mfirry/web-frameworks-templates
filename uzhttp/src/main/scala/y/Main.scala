package y

import java.net.InetSocketAddress
import uzhttp.server.Server
import uzhttp.Response
import uzhttp.websocket.Frame
import zio.{App, ZIO, Task, ExitCode}

object Main extends App {
  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    Server
      .builder(new InetSocketAddress("127.0.0.1", 8080))
      .handleSome {
        case req if req.uri.getPath == "/" =>
          ZIO.succeed(Response.plain("Hello, World!"))
      }
      .serve
      .useForever
      .orDie

  def respondToWebsocketFrame(frame: Frame): Task[Frame] = ???
}
