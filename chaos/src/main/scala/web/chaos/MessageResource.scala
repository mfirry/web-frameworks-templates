package web.chaos

import javax.ws.rs.{ POST, Produces, Path, GET }
import javax.ws.rs.core.MediaType
import com.codahale.metrics.annotation.Timed
import javax.validation.Valid
import scala.util.Random

@Path("json")
@Produces(Array(MediaType.APPLICATION_JSON))
class MessageResource {

  @GET
  @Timed
  def get() = {
    val msg = new Message
    msg.message = "Hello, World!"
    msg
  }

  // @Path("bar")
  // def bar() = new ExampleSubResource
}
