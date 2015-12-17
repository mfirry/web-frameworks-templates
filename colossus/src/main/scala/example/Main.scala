package example

import colossus._
import service._
import protocols.http._
import UrlParsing._
import HttpMethod._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

object Main extends App {

  implicit val io_system = IOSystem()

  Service.become[Http]("sample", 9000) {
    case request @ Get on Root => {
      val json = List(1, 2, 3).asJson.toString
      Callback.successful(request.ok(json))
    }
    case request @ Get on Root / string => {
      Callback.successful(request.ok(((string))))
    }
  }
}