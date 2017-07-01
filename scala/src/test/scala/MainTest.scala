import Main._
import org.scalatest.{FlatSpec, Matchers}
import scala.util.Random.shuffle


class MainTest extends FlatSpec with Matchers {

  "Given an empty list" should "return an empty list" in {

    quickSort(List[Int]()) shouldBe empty
  }

  "Given a list with one element" should "return the same list" in {

    quickSort(List(42)) shouldBe List(42)
  }

  "Given a list with more than one elements" should "return an ordered list" in {

    quickSort(List(7, 9, 0, 3, 4, 1, 5, 8, 2, 6)) shouldBe sorted
  }

  "Given a list with more than one repeated elements" should "return an ordered list" in {

    quickSort(List(3, 2, 2, 3, 1, 3)) shouldBe sorted
  }

  "Given an ordered list with more than one repeated elements" should "count the occurrences preserving the order" in {

    List(1, 2, 2, 3, 3, 3).occurrences.toSeq should contain inOrderOnly(1 -> 1, 2 -> 2, 3 -> 3)
  }

  "Given a shuffled list with more than one repeated elements" should "sort it and count the occurrences" in {

    quickSort(shuffle(List(1, 2, 2, 3, 3, 3))).occurrences.toSeq should contain inOrderOnly(1 -> 1, 2 -> 2, 3 -> 3)
  }
}
