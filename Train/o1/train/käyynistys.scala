package o1.train

object käynnistys extends App {
  val a = new Train("juna")
  val b = new SittingCar(2,2)
  
  a.addCar(b)
  a.addCar(b)
  a.addCar(b)
  
  /*a.addCar(b)
  println( b.seatReservations)
   b.reservePlace(1,'a')
   b.reservePlaces(2)*/
  println()
  println()
  println()
  println()
  println()
}