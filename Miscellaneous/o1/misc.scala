package o1
object misc { // These definitions at the top are discussed in Chapter 5.2.

  // Various small assignments across several chapters will ask you to define functions in this file.
  // Please enter your code below this comment.

//import scala.collection.mutable.Buffer
  import scala.math.min

  def isPortrait(kuva: Pic) = {
    kuva.height > kuva.width
  }
  
  def isLandscape(kuva: Pic) = {
    kuva.height < kuva.width
  }

  def describe(kuva: Pic) = {
    if (isPortrait(kuva))
      "portrait"
    else if (isLandscape(kuva))
      "landscape"
    else
      "square"
  }



  def isInOrder(pairOfNumbers: (Int, Int)) = pairOfNumbers._1 <= pairOfNumbers._2    // This example function is introduced in Chapter 8.4. You can ignore it until then.

  def together(sounds: Vector[String], tempo: Int) = {
    sounds.mkString("&") + "/" + tempo
  }
  
  def tempo(kipale: String): Int = {
    val annettuTempo = kipale.split("/").lift(1)
    annettuTempo match{
      case Some(annettu) => annettu.toInt
      case None => 120
    }
  }
  
  def toMinsAndSecs(luku: Int): (Int, Int) = (luku / 60, luku % 60)
  
  def isAscending(v: Vector[Int]) = {
    v.forall(luku => luku <= v(min((v.indexOf(luku)+1), v.size - 1)))
  }
}