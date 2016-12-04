import com.twitter.finagle.Service
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Status.Ok
import io.fintrospect.RouteSpec
import io.fintrospect.formats.PlainText.ResponseBuilder.implicits._
import io.fintrospect.parameters.Path

object Echo {
  def echo(value: String) = Service.mk { _: Request => Ok(value) }

  val messageParam = Path.string("value")
  val contract = RouteSpec("echo", "echoes the path string").at(Get) / messageParam

  val route = contract bindTo echo
}
