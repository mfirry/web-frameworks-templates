package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

// import akka.http.scaladsl.server.Route
// import akka.http.scaladsl.server.RouteResult.route2HandlerFlow

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import com.typesafe.config.ConfigFactory

import spray.json.DefaultJsonProtocol._

object Main extends App with SprayJsonSupport {
  val config = ConfigFactory.load()

  implicit val system = ActorSystem.create()
  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()

  lazy val route =
    get {
      pathSingleSlash {
        complete(List(1, 2, 3))
      } ~
      path(Segment) { string =>
        complete(string)
      }
    }

  Http().bindAndHandle(route, interface = "localhost", port = 8080)

}
