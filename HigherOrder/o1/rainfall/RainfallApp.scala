package o1.rainfall

import scala.io.StdIn._

object RainfallApp extends App {

  def averageRainfall(data: Vector[Int]) = {
    val cleaned = data.filter( _ >= 0 )
    if (cleaned.isEmpty){
      println("No valid data. Cannot compute average.")
    }else println("The average is " + cleaned.sum / cleaned.size + ".")
  }

  
  val kys = Stream.continually( readLine("Enter rainfall (or 999999 to stop): ").toInt ).takeWhile(_!=999999).toVector
  averageRainfall(this.kys)
}