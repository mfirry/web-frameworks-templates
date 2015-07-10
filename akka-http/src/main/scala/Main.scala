package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import com.typesafe.config.ConfigFactory

import akka.stream.ActorMaterializer

import spray.json.DefaultJsonProtocol._

import akka.stream.scaladsl._
import scala.concurrent._

object Main extends App with SprayJsonSupport {
  val config = ConfigFactory.load()

  implicit val system = ActorSystem.create()

  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()

  lazy val route =
    get {
      path("") {
        complete(List(1, 2, 3))
      } ~
      path(Segment) { string =>
        complete(PrettyPrinter(string.toJson))
      }
    }

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = "localhost", port = 8080)

  val bindingFuture: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection =>
      connection handleWith route
    }).run()

}