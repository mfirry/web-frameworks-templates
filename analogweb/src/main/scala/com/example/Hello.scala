package com.example

import org.analogweb.core.Servers
import org.analogweb.scala.Analogweb

object Hello extends Analogweb {

  // default port is 8000
  def main(args: Array[String]) = Servers.run()

  case class Message(message: String)

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
