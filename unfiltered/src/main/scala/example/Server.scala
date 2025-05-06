package example

import unfiltered.netty

object Server:
  @main def run(): Unit =
    netty.Server
      .http(8080)
      .plan(Example)
      .run()

