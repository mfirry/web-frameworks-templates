package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import spray.json.DefaultJsonProtocol._

object Main extends App with SprayJsonSupport {

  case class Message(message: String)

  object MessageProtocol extends DefaultJsonProtocol {
    implicit val messageFormat = jsonFormat1(Message)
  }

  implicit val system = ActorSystem.create()
  implicit val executionContext = system.dispatcher

  import MessageProtocol._

  lazy val route =
    get {
      pathSingleSlash {
        complete(List(1, 2, 3))
      } ~
        path("json") {
          complete(Message("Hello, World!").toJson)
        } ~
        path("plaintext") {
          complete("Hello, World!")
        } ~
        path("say-hi") {
          parameters("whom") { whom =>
            complete(s"Hello $whom")
          }
        }
    }

  Http().bindAndHandle(route, interface = "localhost", port = 9000)

}
