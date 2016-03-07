
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mfirry/playground/web-frameworks-templates/play/src/main/resources/routes
// @DATE:Mon Mar 07 10:06:35 CET 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseApplication Application = new controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseApplication Application = new controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
