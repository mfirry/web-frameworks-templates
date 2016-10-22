package com.example

import org.analogweb.core.Servers
import org.analogweb.scala.Analogweb

object Hello extends Analogweb {

  def main(args: Array[String]) = Servers.run(8080, "com.example")

  case class Message(message: String)

  get("/plaintext") {
     "Hello, World!"
  }
  get("/json") {
      Ok(asJson(Message("Hello, World!")))
  }

}
