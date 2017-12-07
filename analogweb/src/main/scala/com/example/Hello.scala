package com.example

import analogweb._, circe._, io.circe._, generic.semiauto._
import org.analogweb._
import org.analogweb.core._
import org.analogweb.scala._

object Hello extends Analogweb {

  // default port is 8000
  def main(args: Array[String]) = Servers.run()

  case class Message(message: String)

  implicit val messageEncoder: Encoder[Message] = deriveEncoder[Message]

  get("/json") {
    Ok(asJson(Message("Hello, World!")))
  }
  get("/plaintext") {
    "Hello, World!"
  }
  get("/") {
    Ok(asJson(List(1,2,3)))
  }
  get("/{string}") { implicit r =>
    s"${param("string")}"
  }

}
