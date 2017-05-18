package com.example

import org.analogweb.core.Servers
import org.analogweb.scala.Analogweb

object Hello extends Analogweb {

  // default port is 8000
  def main(args: Array[String]) = Servers.run()

  get("/") {
    Ok(asJson(List(1,2,3)))
  }
  get("/{string}") { implicit r =>
    s"${param("string")}"
  }

}
