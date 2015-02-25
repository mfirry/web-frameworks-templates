package example

import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response._

object Example extends Plan {
  def intent = {
    case GET(Path("/")) => Ok ~> ResponseString("Path requested")
    case GET(Path(Seg("string" :: id :: Nil))) => Ok ~> ResponseString(id)
  }
}
