import com.twitter.finatra._

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object HelloWorldServerMain extends WebServer

class WebServer extends HttpServer {

  override def configureHttp(router: HttpRouter) = {
    router.
      add(new AController)
  }
}
