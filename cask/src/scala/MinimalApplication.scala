package example

object MinimalApplication extends cask.MainRoutes {

  override def main(args: Array[String]): Unit = {
    val server = Undertow.builder
      .addHttpListener(8080, "0.0.0.0")
      // increase io thread count as per https://github.com/TechEmpower/FrameworkBenchmarks/pull/4008
      .setIoThreads(Runtime.getRuntime().availableProcessors() * 2)
      // In HTTP/1.1, connections are persistent unless declared otherwise.
      // Adding a "Connection: keep-alive" header to every response would only
      // add useless bytes.
      .setServerOption[java.lang.Boolean](UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
      .setHandler(defaultHandler)
      .build
    server.start()
  }  
  
  @cask.get("/")
  def hello() = {
    "Hello World!"
  }

  @cask.post("/do-thing")
  def doThing(request: cask.Request) = {
    new String(request.readAllBytes()).reverse
  }

  initialize()
}
