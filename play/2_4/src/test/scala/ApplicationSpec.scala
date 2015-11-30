import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "index renders json with List(1,2,3)" in new WithApplication{
      val index = route(FakeRequest(GET, "/")).get

      status(index) must equalTo(OK)
      contentType(index) must beSome.which(_ == "application/json")
      contentAsString(index) must contain ("[1,2,3]")
    }
  }
}
