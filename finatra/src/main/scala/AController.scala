import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.annotations.QueryParam

case class Message(message: String)

case class Whom(@QueryParam whom: String)

class AController extends Controller {
  get("/") { _: Request =>
    response.created(List(1, 2, 3))
  }

  get("/json") { _: Request =>
    response.created(Message("Hello, World!"))
  }

  get("/plaintext") { _: Request =>
    response.created("Hello, World!")
  }

  get("/say-hi") { hello: Whom =>
    response.created(s"Hello, ${hello.whom}")
  }
}
