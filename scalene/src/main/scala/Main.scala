import scalene.routing._
import BasicConversions._

object Main extends App {

  val route = GET / "hello" as "Hello, World!".ok

  val settings = Settings.basic("example", 8080)

  Routing.start(settings, route)
}