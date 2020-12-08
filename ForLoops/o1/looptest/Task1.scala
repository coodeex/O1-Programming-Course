package o1.looptest

// This program is associated with Chapter 5.5.

object Task1 extends App {

  val numbers = Vector(3.4, 6.5, 2.3, 3, 1.2, 5.41, 5.1, 9.1, 3.4, 7.8, 10, 9.1, 5, 2.1, 1.2)  // Leave this line as it is.

  println("The Beginning.")
  for (current <- this.numbers){
    println("-----")
    println(current * 2)
  }  
  println("The End.")
}
  
/*  println("The Beginning.")
  println("-----")
  println(numbers(0) * 2)
  println("-----")
  println(numbers(1) * 2)
  println("-----")
  println(numbers(2) * 2)
  println("-----")
  println(numbers(3) * 2)
  println("-----")
  println(numbers(4) * 2)
  println("-----")
  println(numbers(5) * 2)
  println("-----")
  println(numbers(6) * 2)
  println("-----")
  println(numbers(7) * 2)
  println("-----")
  println(numbers(8) * 2)
  println("-----")
  println(numbers(9) * 2)
  println("-----")
  println(numbers(10) * 2)
  println("-----")
  println(numbers(11) * 2)
  println("-----")
  println(numbers(12) * 2)
  println("-----")
  println(numbers(13) * 2)
  println("-----")
  println(numbers(14) * 2)
  println("The End.")

} */

