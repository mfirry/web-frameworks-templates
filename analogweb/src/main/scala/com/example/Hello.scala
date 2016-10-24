package com.example

import org.analogweb.core.Servers
import org.analogweb.scala.Analogweb

object Hello extends Analogweb {

  // default port is 8000
  def main(args: Array[String]) = Servers.run()

  case class Message(message: String)

  get("/plaintext") {
    "Hello, World!"
  }
  get("/json") {
    Ok(asJson(Message("Hello, World!")))
  }

}
