package com.example

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import spray.json._

object Main extends App with SprayJsonSupport {

  case class Message(message: String)

  object MessageProtocol extends DefaultJsonProtocol {
    implicit val messageFormat: JsonFormat[Message] = jsonFormat1(Message.apply)
  }

  implicit val system: ActorSystem = ActorSystem.create()
  implicit val executionContext: ExecutionContext = system.dispatcher

  import MessageProtocol._

  lazy val route =
    get {
      pathSingleSlash {
        complete(Array(1, 2, 3))
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

  Http().newServerAt(interface = "localhost", port = 9000).bind(route)

}
