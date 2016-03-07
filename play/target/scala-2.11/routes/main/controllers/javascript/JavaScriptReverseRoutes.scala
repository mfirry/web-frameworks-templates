
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/src/main/resources/routes
// @DATE:Mon Mar 07 10:06:35 CET 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:1
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def plaintext: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.plaintext",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "plaintext"})
        }
      """
    )
  
    // @LINE:4
    def json: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.json",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "json"})
        }
      """
    )
  
    // @LINE:1
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:2
    def getString: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.getString",
      """
        function(string0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "string/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("string", encodeURIComponent(string0))})
        }
      """
    )
  
  }


}
