import com.twitter.finatra._

class WebServer extends Controller {

  get("/") { request =>
    render.json(List(1, 2, 3)).toFuture
  }
  get("/string/:string") { request =>
    val string = request.routeParams.get("string").getOrElse("")
    render.json(string).toFuture
  }

}

object App extends FinatraServer {
  register(new WebServer())
}