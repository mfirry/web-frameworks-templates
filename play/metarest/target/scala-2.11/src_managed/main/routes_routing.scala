// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/metarest/conf/routes
// @HASH:ee73c19f22f3b00f449552bd5b9066f0cd94e004
// @DATE:Mon Apr 27 15:41:38 CEST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:1
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:2
private[this] lazy val controllers_Application_get1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("string/"),DynamicPart("string", """[^/]+""",true))))
private[this] lazy val controllers_Application_get1_invoker = createInvoker(
controllers.Application.get(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "get", Seq(classOf[String]),"GET", """""", Routes.prefix + """string/$string<[^/]+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """string/$string<[^/]+>""","""controllers.Application.get(string:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:1
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
   }
}
        

// @LINE:2
case controllers_Application_get1_route(params) => {
   call(params.fromPath[String]("string", None)) { (string) =>
        controllers_Application_get1_invoker.call(controllers.Application.get(string))
   }
}
        
}

}
     