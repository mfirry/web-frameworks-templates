package web.chaos

import com.google.inject.Scopes
import mesosphere.chaos.http.RestModule

class ExampleRestModule extends RestModule {

  protected override def configureServlets() {
    super.configureServlets()

    bind(classOf[MessageResource]).in(Scopes.SINGLETON)
  }

}
