import org.mashupbots.socko.routes._
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.webserver.{WebServer => SockoWebServer}
import org.mashupbots.socko.webserver.WebServerConfig
import org.mashupbots.socko.events.HttpRequestEvent

import akka.actor.ActorSystem
import akka.actor.Props

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

object WebServer extends App {

  val system = ActorSystem.create

  val routes = Routes({
    case GET(request) =>
      request match {
        case event: HttpRequestEvent =>
          event.response.write(compact(render(List(1,2,3))))
      }
  })

  val webServer = new SockoWebServer(WebServerConfig(), routes, system)
    webServer.start()
}