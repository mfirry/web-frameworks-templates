package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow

import com.typesafe.config.ConfigFactory

import akka.stream.ActorFlowMaterializer

import akka.stream.scaladsl._
import scala.concurrent._

object TechEmpowerMain {
  val config = ConfigFactory.load()

  implicit val system = ActorSystem.create()

  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorFlowMaterializer()

  lazy val route =
    get {
      path("json") {
        import spray.json._
        import spray.json.DefaultJsonProtocol._
        import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
        complete(JsObject("message" -> JsString("Hello, World!")))
      } ~
      path("plaintext") {
        complete("Hello, World!")
      }
    }

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = "localhost", port = 8081)

  val bindingFuture: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection =>
      connection handleWith route
    }).run()

}