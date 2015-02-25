import com.twitter.util.Await
import java.net.InetSocketAddress
import io.finch._
import io.finch.json._
import io.finch.request._
import io.finch.response._
import com.twitter.finagle._
import com.twitter.finagle.Httpx
import com.twitter.finagle.httpx._
import com.twitter.finagle.httpx.path._

object WebServer extends App {

  Await.ready(
    Httpx.serve(
      new InetSocketAddress("localhost", 9000), MyEndpoint))
}

object MyEndpoint extends Endpoint[HttpRequest, HttpResponse] {

  def route = {
    case Method.Get -> Root =>
      Service.mk(req => {
        Ok(Json.arr(1, 2, 3)).toFuture
      })
  }

}