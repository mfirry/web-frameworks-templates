package example

import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response._

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}

object Example extends Plan {
  implicit val formats = DefaultFormats

  case class Message(message: String)

  def intent = {
    case GET(Path("/")) => Ok ~> Json(List(1, 2, 3))
    case GET(Path("/json")) => Ok ~> Json(write(Message("Hello, World!")))
    case GET(Path("/plaintext")) => Ok ~> ResponseString("Hello, World!")
    case GET(Path(Seg(string :: Nil))) => Ok ~> ResponseString(string)
  }
}
