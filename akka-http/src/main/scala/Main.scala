package com.example

import akka.actor.ActorSystem
import akka.http.Http
// import akka.http.marshalling.ToResponseMarshallable._

import akka.http.server.Directives._
import akka.http.server.Route
import akka.http.server.RouteResult.route2HandlerFlow

import spray.json._
import akka.http.marshallers.sprayjson.SprayJsonSupport

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._

import akka.stream.ActorFlowMaterializer

import spray.json.DefaultJsonProtocol._

object Main extends App with SprayJsonSupport {
  val config = ConfigFactory.load()

  val name = config.as[String]("actor-system.name")

  implicit val system = ActorSystem(name)

  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorFlowMaterializer()

  lazy val route =
    get {
      path("") {
        complete(List(1, 2, 3))
      } ~
      path(Segment) { string =>
        complete(PrettyPrinter(string.toJson))
      }
    }

  val serverBinding =
    Http(system)
      .bind(interface = "localhost", port = 8080)
      .startHandlingWith(route)
}