import org.mashupbots.socko.routes._
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.webserver.{WebServer => SockoWebServer}
import org.mashupbots.socko.webserver.WebServerConfig
import org.mashupbots.socko.events.HttpRequestEvent

import akka.actor.{Actor, ActorSystem, Props}

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

object WebServer extends App {

  val system = ActorSystem.create

  val routes = Routes({
    case HttpRequest(request) => request match {
        case GET(Path("/")) =>
          system.actorOf(Props(ListAnswarer())) ! request
        case GET(path) => path match {
            case PathSegments(str :: Nil) =>
              system.actorOf(Props(StringAnswarer(str))) ! request
            }
       }
  })

  case class ListAnswarer() extends Actor {
    def receive = {
      case event: HttpRequestEvent =>
        event.response.write(compact(render(List(1,2,3))))
        context.stop(self)
    }
  }

  case class StringAnswarer(str: String) extends Actor {
    def receive = {
      case event: HttpRequestEvent =>
        event.response.write(compact(render(str)))
        context.stop(self)
    }
  }

  val webServer = new SockoWebServer(WebServerConfig(), routes, system)
    webServer.start()
}