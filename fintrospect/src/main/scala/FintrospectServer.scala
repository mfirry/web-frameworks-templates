import com.twitter.finagle.Http
import com.twitter.finagle.http.filter.Cors
import com.twitter.finagle.http.filter.Cors.HttpFilter
import com.twitter.finagle.http.path.Root
import com.twitter.util.Await
import io.fintrospect.ModuleSpec

object FintrospectServer extends App {

  private val svc = ModuleSpec(Root)
    .withRoute(Echo.route)
    .withRoute(JsonList.route)
    .toService

  Await.ready(Http.serve(":9999", new HttpFilter(Cors.UnsafePermissivePolicy).andThen(svc)))
}

