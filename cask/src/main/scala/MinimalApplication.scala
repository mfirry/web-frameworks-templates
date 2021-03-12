package example

object MinimalApplication extends cask.MainRoutes {

  @cask.get("/plaintext")
  def plaintext() = {
    "Hello World!"
  }

  @cask.getJson("/json")
  def j() = {
    import upickle.default._
    cask.Response(data = write(Message("Hello World!")))
  }

  initialize()
}
