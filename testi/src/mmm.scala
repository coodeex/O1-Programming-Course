import scala.io.StdIn._

object ExampleApp1 extends App {
  val userInputs = Stream.continually( readLine("Enter a number: ") )
  println(userInputs.take(4).mkString(","))
  println(userInputs.take(4).map( _.toInt ).product)
}