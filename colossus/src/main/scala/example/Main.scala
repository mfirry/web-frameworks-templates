package example

import colossus._
import core._
import service._
import protocols.http._
import UrlParsing._
import HttpMethod._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

class HelloService(context: ServerContext) extends RequestHandler(context) {
  case class Message(message: String)
  def handle = {
    case request @ Get on Root / "json" => {
      Callback.successful(request.ok(Message("Hello, World!").asJson.toString))
    }
    case request @ Get on Root / "plaintext" => {
      Callback.successful(request.ok("Hello, World!"))
    }
    case request @ Get on Root => {
      val json = List(1, 2, 3).asJson.toString
      Callback.successful(request.ok(json))
    }
    case request @ Get on Root / string => {
      Callback.successful(request.ok(((string))))
    }
  }
}

object Main extends App {

  import akka.actor._
  implicit val actorSystem = ActorSystem("COLOSSUS")

  implicit val io_system = IOSystem()

  // HttpServer.start("hello-world", 9000){ worker => new HelloInitializer(worker) }
  HttpServer.start("hello-world", 9000){ init =>
    new Initializer(init) {
      def onConnect = context => new HelloService(context)
    }
  }


}
