package example

import unfiltered.jetty

object Server {
  def main(args: Array[String]) {
    jetty.Server
      .http(8080)
      .plan(Example)
      .run()
  }
}