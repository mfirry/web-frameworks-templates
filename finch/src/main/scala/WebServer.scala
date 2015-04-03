import io.finch.micro._
import io.finch.route.{Endpoint => _, _}
import io.finch.json._

import com.twitter.finagle.Httpx
import com.twitter.util.Await

object WebServer extends App {

  val api: Endpoint =
    Get /> Micro.value(Json.arr(1, 2, 3)) |
    Get / string /> Micro.value

  Await.ready(Httpx.serve(":9000", api))
}