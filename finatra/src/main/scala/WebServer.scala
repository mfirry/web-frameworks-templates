import com.twitter.finatra._

class WebServer extends Controller {

  get("/") { request =>
    render.json(List(1, 2, 3)).toFuture
  }

}

object App extends FinatraServer {
  register(new WebServer())
}