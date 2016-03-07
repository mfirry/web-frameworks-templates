
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/src/main/resources/routes
// @DATE:Mon Mar 07 10:06:35 CET 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
