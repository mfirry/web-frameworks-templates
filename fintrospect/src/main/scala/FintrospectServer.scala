import com.twitter.finagle.Http
import com.twitter.finagle.http.filter.Cors
import com.twitter.finagle.http.filter.Cors.HttpFilter
import com.twitter.finagle.http.path.Root
import com.twitter.util.Await
import io.fintrospect.ModuleSpec
import io.fintrospect.renderers.swagger2dot0.{ApiInfo, Swagger2dot0Json}

object FintrospectServer extends App {

  private val svc = ModuleSpec(Root, Swagger2dot0Json(ApiInfo("Example app", "1.0")))
    .withDescriptionPath(_ / "api-docs")
    .withRoute(Echo.route)
    .withRoute(JsonList.route)
    .toService

  Await.ready(Http.serve(":9999", new HttpFilter(Cors.UnsafePermissivePolicy).andThen(svc)))
}

