package example

import unfiltered.netty

object Server {
  def main(args: Array[String]): Unit = {
    netty.Server
      .http(8080)
      .plan(Example)
      .run()
  }
}
