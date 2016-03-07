
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/src/main/resources/routes
// @DATE:Mon Mar 07 10:06:35 CET 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  Application_0: controllers.Application,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    Application_0: controllers.Application
  ) = this(errorHandler, Application_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Application.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """string/""" + "$" + """string<[^/]+>""", """controllers.Application.getString(string:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """json""", """controllers.Application.json"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """plaintext""", """controllers.Application.plaintext"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_Application_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    Application_0.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:2
  private[this] lazy val controllers_Application_getString1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("string/"), DynamicPart("string", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Application_getString1_invoker = createInvoker(
    Application_0.getString(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "getString",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """string/""" + "$" + """string<[^/]+>"""
    )
  )

  // @LINE:4
  private[this] lazy val controllers_Application_json2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("json")))
  )
  private[this] lazy val controllers_Application_json2_invoker = createInvoker(
    Application_0.json,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "json",
      Nil,
      "GET",
      """""",
      this.prefix + """json"""
    )
  )

  // @LINE:5
  private[this] lazy val controllers_Application_plaintext3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("plaintext")))
  )
  private[this] lazy val controllers_Application_plaintext3_invoker = createInvoker(
    Application_0.plaintext,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "plaintext",
      Nil,
      "GET",
      """""",
      this.prefix + """plaintext"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(Application_0.index)
      }
  
    // @LINE:2
    case controllers_Application_getString1_route(params) =>
      call(params.fromPath[String]("string", None)) { (string) =>
        controllers_Application_getString1_invoker.call(Application_0.getString(string))
      }
  
    // @LINE:4
    case controllers_Application_json2_route(params) =>
      call { 
        controllers_Application_json2_invoker.call(Application_0.json)
      }
  
    // @LINE:5
    case controllers_Application_plaintext3_route(params) =>
      call { 
        controllers_Application_plaintext3_invoker.call(Application_0.plaintext)
      }
  }
}
