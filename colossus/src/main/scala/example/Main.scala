package example

import colossus._
import service._
import protocols.http._
import UrlParsing._
import HttpMethod._

object Main extends App {

  implicit val io_system = IOSystem()

  Service.become[Http]("http-echo", 9000){
    case request @ Get on Root => request.ok("Hello world!")
  }
}