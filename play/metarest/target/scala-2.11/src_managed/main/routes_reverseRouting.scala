// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/metarest/conf/routes
// @HASH:ee73c19f22f3b00f449552bd5b9066f0cd94e004
// @DATE:Mon Apr 27 15:41:38 CEST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:2
// @LINE:1
package controllers {

// @LINE:2
// @LINE:1
class ReverseApplication {


// @LINE:1
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:2
def get(string:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "string/" + implicitly[PathBindable[String]].unbind("string", dynamicString(string)))
}
                        

}
                          
}
                  


// @LINE:2
// @LINE:1
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:2
// @LINE:1
class ReverseApplication {


// @LINE:1
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:2
def get : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.get",
   """
      function(string) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "string/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("string", encodeURIComponent(string))})
      }
   """
)
                        

}
              
}
        


// @LINE:2
// @LINE:1
package controllers.ref {


// @LINE:2
// @LINE:1
class ReverseApplication {


// @LINE:1
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:2
def get(string:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.get(string), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "get", Seq(classOf[String]), "GET", """""", _prefix + """string/$string<[^/]+>""")
)
                      

}
                          
}
        
    