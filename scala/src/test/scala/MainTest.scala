import org.scalatest.{FlatSpec, Matchers}
import Main.quickSort


class MainTest extends FlatSpec with Matchers {

  "Given an empty list" should "return an empty list" in {

    quickSort(List[Int]()) shouldBe List[Int]()
  }

  "Given a list with one element" should "return the same list" in {

    quickSort(List(42)) shouldBe List(42)
  }

  "Given a list with more than one elements" should "return an ordered list" in {

    quickSort(List(7, 9, 0, 3, 4 , 1, 5, 8, 2, 6)) shouldBe List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  "Given a list with more than one repeated elements" should "return an ordered list" in {

    quickSort(List(3, 2, 2, 3, 1, 3)) shouldBe List(1, 2, 2, 3, 3, 3)
  }
}
