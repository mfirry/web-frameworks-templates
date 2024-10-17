package example

object MinimalRoutes extends cask.MainRoutes {

  @cask.get("/")
  def list() = {
    import upickle.default._
    cask.Response(data = write(List(1,2,3)))
  }      

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
