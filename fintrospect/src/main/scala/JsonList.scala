import com.twitter.finagle.Service
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.Request
import io.fintrospect.RouteSpec
import io.fintrospect.formats.Circe.JsonFormat._
import io.fintrospect.formats.Circe.ResponseBuilder._

object JsonList {

  val jsonList = Service.mk { _: Request => Ok(array(number(1L), number(2L), number(3L))) }

  val route = RouteSpec("json list", "marshals a list of ints using circe").at(Get) bindTo jsonList
}
