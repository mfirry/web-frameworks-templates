package example

import unfiltered.request._
import unfiltered.response._
import unfiltered.netty._

import org.json4s.JsonDSL._

@io.netty.channel.ChannelHandler.Sharable
object Example
    extends cycle.Plan
    with cycle.SynchronousExecution
    with ServerErrorResponse {
  @scala.annotation.nowarn
  def intent = {
    case GET(Path(Seg(string :: Nil))) => Ok ~> ResponseString(string)
    case GET(Path("/"))                => Ok ~> Json(List(1, 2, 3))
  }
}
