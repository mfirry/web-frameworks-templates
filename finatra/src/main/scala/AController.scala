import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import com.twitter.finatra.request.RouteParam

case class Hello(@RouteParam msg: String)

class AController extends Controller {
  get("/") { request: Request =>
    response.created(List(1, 2, 3))
  }

  get("/string/:msg") { hello: Hello =>
    response.created(hello.msg)
  }
}