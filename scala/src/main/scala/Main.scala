import scala.collection.immutable.ListMap.empty
import scala.io.Source.fromResource

object Main extends App {

  implicit class ExtendedList[T](t: List[T]) {

    def occurrences: Map[T, Int] =
      t.foldLeft(empty[T, Int]) { (a, v) => a + a.get(v).fold(v -> 1)(x => v -> (x + 1)) }
  }

  def quickSort(numbers: List[Int]): List[Int] = numbers match {
    case Nil => Nil
    case head :: tail =>
      val (lower, upper) = tail.partition(_ < head)
      quickSort(lower) ++ List(head) ++ quickSort(upper)
  }

  val numbers = fromResource("source").getLines.map(_.toInt).toList

  quickSort(numbers).occurrences.foreach(x => println(s"${x._1}=${x._2}"))
}
