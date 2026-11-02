package yolo.models

import io.eezo.core.Id
import io.eezo.db.Table
import io.eezo.http.{Api, Form, Resource}
import io.eezo.http.{Action, Actions}
import io.eezo.json.upickle.given

final case class Name(id: Id[Name], name: String) derives Table, Form, Resource, Api

object Name {
  given Actions[Name] = Actions.except(Action.Destroy)
}
