package yolo

import io.eezo.generated.Routes
import io.eezo.http.HttpApp
import io.eezo.http.RouteTable

object Main extends HttpApp {
  override def routes: RouteTable = Routes.table()
}