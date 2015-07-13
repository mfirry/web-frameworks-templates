import akka.actor.{ActorRefFactory, ActorSystem}
import com.github.vonnagy.service.container.ContainerBuilder
import com.github.vonnagy.service.container.http.routing.RoutedEndpoints
import com.typesafe.config.ConfigFactory
import spray.http.MediaTypes._

object WebServer extends App {

  // Here we establish the container and build it while
  // applying extras.
  val service = new ContainerBuilder()
    // Set the port to 9000
    .withConfig(ConfigFactory.parseString("container.http.port=9000"))
    // Register the API routes
    .withRoutes(classOf[TestEndpoints]).build

  service.start
}

class TestEndpoints(implicit system: ActorSystem,
                    actorRefFactory: ActorRefFactory) extends RoutedEndpoints {

  // Import the default Json marshaller
  implicit val marshaller = jsonMarshaller

  val route = {
    path("") {
      respondWithMediaType(`application/json`) {
        complete(List(1, 2, 3))
      }
    } ~
    path(Segment) { string =>
      complete(string)
    }
  }


}