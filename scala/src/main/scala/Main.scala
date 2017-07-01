import scala.io.Source.fromResource

object Main extends App {

  def quickSort(numbers: List[Int]): List[Int] = numbers match {
    case Nil => Nil
    case head :: tail =>
      val (lower, upper) = tail.partition(_ < head)
      quickSort(lower) ++ List(head) ++ quickSort(upper)
  }

  val numbers = fromResource("source").getLines.map(_.toInt).toList

  quickSort(numbers).foreach(println)
}
