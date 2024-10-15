package example

case class MinimalRoutes()(implicit cc: castor.Context, log: cask.Logger)
    extends cask.Routes {

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
object MinimalApplication extends cask.Main {
  val allRoutes = Seq(MinimalRoutes())
}
