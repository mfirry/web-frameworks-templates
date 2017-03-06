package com.example

import akka.http.scaladsl.server.{HttpApp, Route}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol._

object Main extends HttpApp with SprayJsonSupport with App {

  def route: Route =
    get {
      pathSingleSlash {
        complete(List(1, 2, 3))
      } ~
      path(Segment) { string =>
        complete(string)
      }
    }

  startServer(host = "localhost", port = 8080)
}
