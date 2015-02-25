package com.example

import akka.actor.ActorSystem
import akka.http.Http
import akka.http.marshalling.ToResponseMarshallable.apply
import akka.http.server.Directive.addByNameNullaryApply
import akka.http.server.Directive.addDirectiveApply
import akka.http.server.Directives.IntNumber
import akka.http.server.Directives.complete
import akka.http.server.Directives.get
import akka.http.server.Directives.path
import akka.http.server.Directives.segmentStringToPathMatcher
import akka.http.server.Route
import akka.http.server.RouteResult.route2HandlerFlow

import spray.json._
import akka.http.marshallers.sprayjson.SprayJsonSupport

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._

import akka.stream.ActorFlowMaterializer

object Main extends App with SprayJsonSupport {
  val config = ConfigFactory.load()

  val name = config.as[String]("actor-system.name")

  implicit val system = ActorSystem(name)

  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorFlowMaterializer()

  lazy val route =
    path("") {
      get {
        import spray.json.DefaultJsonProtocol._
        val ast = List(1, 2, 3).toJson
        complete(ast)
      }
    }

  val serverBinding =
    Http(system)
      .bind(interface = "localhost", port = 8080)
      .startHandlingWith(route)

}