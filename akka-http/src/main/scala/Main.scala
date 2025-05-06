package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.stream.scaladsl._

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route.seal

import scala.concurrent._
import scala.concurrent.duration._

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

object Main extends App with SprayJsonSupport {

  implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  case class Message(message: String)

  object MessageProtocol extends DefaultJsonProtocol {
    implicit val messageFormat: RootJsonFormat[Message] = jsonFormat1(Message.apply)
  }

  import MessageProtocol._

  lazy val route =
    get {
      pathSingleSlash {
        complete(List(1, 2, 3).toJson)
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

  import scala.io.StdIn
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
