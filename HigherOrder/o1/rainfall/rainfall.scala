package o1

package object rainfall {

  def averageRainfall(vek: Vector[Int]) = { //vähän hutioloitu täällä
    val montako = (vek.takeWhile( _ != 999999).filter( _ >= 0).length)
    val kuiMonta = vek.takeWhile( _ != 999999).filter( _ >= 0).reduceLeftOption(_ + _)
    kuiMonta match {
      case Some(jotai) => Some((vek.takeWhile( _ != 999999).filter( _ >= 0).sum / montako))
      case None => None
    }
    
  }


  def drySpell(vek: Vector[Int], length: Int) = {
     vek.sliding(length).toVector.indexWhere(vekt => vekt.forall(luku => luku>= 0 && luku<=5))
  }




}