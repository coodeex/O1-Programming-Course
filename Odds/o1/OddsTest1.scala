package o1

// This program is developed in Chapters 2.7 and 3.4.
// It creates a single Odds object and uses some of its methods.

import scala.io.StdIn._

object OddsTest1 extends App {

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val firstInput = readInt()
  val secondInput = readInt()
  
  val tod = new Odds(this.firstInput, this.secondInput)

  println("The odds you entered are:")
  println("In fractional format: " + this.tod.fractional)
  println("In decimal format: " + this.tod.decimal)
  println("In moneyline format: " + this.tod.moneyline)
  println("Event probability: " + this.tod.probability)
  println("Reverse odds: " + this.tod.not)
  println("Odds of happening twice: " + this.tod.both(tod))
  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim " + thirdInput * this.tod.decimal)

  println("Please enter the odds of a second event as two integers on separate lines.")
  val firstInput2 = readInt()
  val secondInput2 = readInt()
  val tod2 = new Odds(this.firstInput2, this.secondInput2)
  println("The odds of both events happening are: " + this.tod.both(tod2))
  println("The odds of one or both happening are: " + this.tod.either(tod2))
}
