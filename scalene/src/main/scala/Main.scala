import scalene.routing._
import BasicConversions._

object Main extends App {

  val routeP = "plaintext" as "Hello, World!".ok

  val routeJ = "json" as "Hello, World!".ok

  val settings = Settings.basic("example", 8080)

  Routing.start(settings, Routes(routeJ, routeP))
}