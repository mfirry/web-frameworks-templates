import com.twitter.finagle.Http
import com.twitter.util.Await

object FintrospectClient extends App {
  val clientFunction = Echo.contract.bindToClient(Http.newService("localhost:9999"))

  println(Await.result(clientFunction(Echo.messageParam --> "bob")).contentString)
}
