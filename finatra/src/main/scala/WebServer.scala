import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object HelloWorldServerMain extends WebServer

class WebServer extends HttpServer {

  override val defaultHttpPort: String = ":9000"

  override def configureHttp(router: HttpRouter) = {
    val _ = router.add(new AController)
  }
}
