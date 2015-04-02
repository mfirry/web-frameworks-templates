import java.net.InetSocketAddress

import io.finch._
import io.finch.{Endpoint => _, _}
import io.finch.json._
import io.finch.route._
import io.finch.route.Endpoint
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx

import com.twitter.util.Await

object WebServer extends App {

  val  oneTwoThree: Endpoint[HttpRequest, HttpResponse] = Get /> Ok(Json.arr(1, 2, 3)).toFuture
  val simpleString: Endpoint[HttpRequest, HttpResponse] = Get / string /> { Ok(_).toFuture }

  val api: Service[HttpRequest, HttpResponse] = (oneTwoThree | simpleString)

  Await.ready(
    Httpx.serve(
      "localhost:9000", api
    )
  )
}