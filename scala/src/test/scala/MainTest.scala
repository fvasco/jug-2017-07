import Main._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random.shuffle


class MainTest extends FlatSpec with Matchers {

  "Given an empty list" should "return an empty list" in {

    List[Int]().quickSort shouldBe empty
  }

  "Given a list with one element" should "return the same list" in {

    List(42).quickSort shouldBe List(42)
  }

  "Given a list with more than one elements" should "return an ordered list" in {

    List(7, 9, 0, 3, 4, 1, 5, 8, 2, 6).quickSort shouldBe sorted
  }

  "Given a list with more than one repeated elements" should "return an ordered list" in {

    List(3, 2, 2, 3, 1, 3).quickSort shouldBe sorted
  }

  "Given an ordered list with more than one repeated elements" should "count the occurrences preserving the order" in {

    List(1, 2, 2, 3, 3, 3).occurrences.toSeq should contain inOrderOnly(1 -> 1, 2 -> 2, 3 -> 3)
  }

  "Given a shuffled list with more than one repeated elements" should "sort it and count the occurrences" in {

    shuffle(List(1, 2, 2, 3, 3, 3)).quickSort.occurrences.toSeq should contain inOrderOnly(1 -> 1, 2 -> 2, 3 -> 3)
  }
}
