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

class HelloService(context: ServerContext) extends HttpService(ServiceConfig(), context) {
  def handle = {
    case request @ Get on Root => {
      val json = List(1, 2, 3).asJson.toString
      Callback.successful(request.ok(json))
    }
    case request @ Get on Root / string => {
      Callback.successful(request.ok(((string))))
    }
  }
}

class HelloInitializer(worker: WorkerRef) extends Initializer(worker) {

  def onConnect = context => new HelloService(context)

}

object Main extends App {

  implicit val io_system = IOSystem()

  Server.start("hello-world", 9000){ worker => new HelloInitializer(worker) }

}
