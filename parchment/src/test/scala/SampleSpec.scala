import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._
import org.scalactic._

class SampleSpec extends AnyFlatSpec with should.Matchers {
  "ExampleSpec" should "run" in {
    assert(Main.main(Array.empty[String]) === ())
  }
}
