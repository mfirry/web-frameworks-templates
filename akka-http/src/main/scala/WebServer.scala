
import akka.actor.ActorSystem
import akka.io.IO
import akka.http.{Http,HttpExt}
import akka.util.Timeout
import akka.http.marshallers.sprayjson.SprayJsonSupport._
import akka.http.unmarshalling._
import akka.stream.{ ActorFlowMaterializerSettings, ActorFlowMaterializer }
import akka.http.model.HttpMethods._
import akka.stream.scaladsl.{ Flow, Sink }
import scala.concurrent.Future
import scala.concurrent.duration._
import akka.http.model._
import spray.json.DefaultJsonProtocol._
import spray.json._
import akka.stream.javadsl.Flow

//http://doc.akka.io/docs/akka-stream-and-http-experimental/0.4/scala/http-core-server.html
object WebServer extends App {
	
   implicit val system = ActorSystem.create()
   import system.dispatcher
   implicit val materializer = ActorFlowMaterializer(ActorFlowMaterializerSettings(system))
   
   val serverBinding = Http(system).bind(interface = "localhost", port = 8080)
   serverBinding.mapAsync(connection => {// foreach materializes the source {
   		println("Accepted new connection from " + connection.remoteAddress)
   		Future.successful("ciao")
   	}
   )

   val bindingFuture = serverBinding.to(Sink.foreach { connection =>
   		connection handleWithSyncHandler requestHandler
   }).run()
   
   val requestHandler: HttpRequest => HttpResponse = {
   	case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
   		HttpResponse(entity = PrettyPrinter(List(1,2,3).toJson))
   	case HttpRequest(GET, Uri(_,_,Uri.Path.Slash(str),_,_), _, _, _) => //Enjoy underscores!
   	    HttpResponse(entity = PrettyPrinter(str.toString.toJson))
   }
}