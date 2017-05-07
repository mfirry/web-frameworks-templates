package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import code.api.RestExample

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    LiftRules.statelessDispatch.append(RestExample)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    
    def hasTrailingIndex_?(path: ParsePath): Boolean = path.partPath.lastOption.contains("index")

    // Rewriting /index to /
    LiftRules.statelessRewrite.append {
      case RewriteRequest(path, _, _) if hasTrailingIndex_?(path) =>
        RewriteResponse(
          path.copy(partPath = path.partPath.dropRight(1)),
          Map(),
          stopRewriting = true
        )
    }
  }
}
