package yolo

import io.eezo.EezoApp
import io.eezo.db.Schema
import io.eezo.generated.Routes
import io.eezo.http.RouteTable

object Main extends EezoApp {
  override def routes: RouteTable = Routes.table()
  override def schema: Schema = models.AppSchema
}