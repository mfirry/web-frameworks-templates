import java.net.InetSocketAddress

import io.finch._
import io.finch.json._
import io.finch.route._
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx

import com.twitter.util.Await

object WebServer extends App {

  private[this] def simpleStringService(s: String) = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest) = Ok(s"$s").toFuture
  }

  val oneTwoThree: io.finch.route.Endpoint[HttpRequest, HttpResponse] = Get /> Ok(Json.arr(1, 2, 3)).toFuture
  val simpleString: io.finch.route.Endpoint[HttpRequest, HttpResponse] = Get / "string" / string /> simpleStringService

  Await.ready(
    Httpx.serve(
      new InetSocketAddress("localhost", 9000),
      (oneTwoThree | simpleString)
    )
  )
}