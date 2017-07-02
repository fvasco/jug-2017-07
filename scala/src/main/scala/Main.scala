import scala.collection.immutable.ListMap.empty
import scala.io.Source.fromResource

object Main extends App {

  implicit class ExtendedList[T](t: List[T]) {

    def occurrences: Map[T, Int] =
      t.foldLeft(empty[T, Int]) { (a, v) => a + a.get(v).fold(v -> 1)(x => v -> (x + 1)) }

    def quickSort(implicit f: T => Ordered[T]): List[T] = t match {
      case Nil => Nil
      case head :: tail =>
        val (lower, upper) = tail.partition(_ < head)
        lower.quickSort ++ List(head) ++ upper.quickSort
    }
  }

  fromResource("source").getLines.map(_.toInt).toList
    .quickSort
    .occurrences
    .foreach(x => println(s"${x._1}=${x._2}"))
}
