package com.example

import analogweb._, circe._, io.circe._, generic.semiauto._

object Hello {

  // default port is 8000
  def main(args: Array[String]) = {
    val port = sys.props.get("http.port").getOrElse("8000")
    val uri = "0.0.0.0"
    http(uri, port.toInt)(routes).run
  }

  case class Message(message: String)

  implicit val messageEncoder: Encoder[Message] = deriveEncoder[Message]

  val routes =
    get("/json") { _ =>
      Ok(asJson(Message("Hello, World!")))
    } ++
      get("/plaintext") { _ =>
        "Hello, World!"
      } ++
      get("/") { _ =>
        Ok(asJson(List(1, 2, 3)))
      } ++
      get("/say-hi") { implicit r =>
        s"Hi: ${r.query("whom")}"
      }

}
