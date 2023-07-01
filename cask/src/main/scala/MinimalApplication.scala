package example

@scala.annotation.nowarn // https://github.com/com-lihaoyi/cask/issues/62
object MinimalApplication extends cask.MainRoutes {

  @cask.get("/plaintext")
  def plaintext(): String = "Hello World!"

  @cask.getJson("/json")
  def j() = {
    import upickle.default._
    cask.Response(data = write(Message("Hello World!")))
  }

  initialize()
}
