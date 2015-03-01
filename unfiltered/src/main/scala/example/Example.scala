package example

import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response._

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

object Example extends Plan {
  def intent = {
    case GET(Path("/")) => Ok ~> Json(List(1, 2, 3))
    case GET(Path(Seg(string :: Nil))) => Ok ~> ResponseString(string)
  }
}
