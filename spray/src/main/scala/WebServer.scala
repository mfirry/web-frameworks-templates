import akka.actor.{ Actor, ActorSystem, Props }
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout

import spray.can.Http
import spray.routing._
import spray.http._; import MediaTypes._
import spray.httpx.SprayJsonSupport._

import scala.concurrent.duration._

object WebServer extends App {

  implicit val system = ActorSystem("spray-example")

  val service = system.actorOf(Props[DemoServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8080)
}

class DemoServiceActor extends Actor with DemoService {
  def actorRefFactory = context
  def receive = runRoute(route)
}

trait DemoService extends HttpService {
  import spray.util._
  import spray.json._
  import DefaultJsonProtocol._

  val route =
    path("") {
      get {
        complete(List(1, 2, 3))
      }
    } ~
    path(Segment) { thingy =>
      get {
        complete(thingy)
      }
    }
}
