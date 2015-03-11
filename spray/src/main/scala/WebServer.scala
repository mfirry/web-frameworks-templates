import akka.actor.{ Actor, ActorSystem, Props }
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout

import spray.can.Http
import spray.routing._
import spray.http._; import MediaTypes._
import org.json4s._
import org.json4s.jackson.Serialization.formats
import spray.httpx.Json4sJacksonSupport


import scala.concurrent.duration._

object WebServer extends App {

  implicit val system = ActorSystem("spray-example")

  val service = system.actorOf(Props[DemoServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}

class DemoServiceActor extends Actor with DemoService {
  def actorRefFactory = context
  def receive = runRoute(route)
}

object Support extends Json4sJacksonSupport {
  implicit val json4sJacksonFormats = formats(NoTypeHints)
}

trait DemoService extends HttpService {
  import Support._

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
