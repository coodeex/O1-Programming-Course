package o1.robots

import o1.CompassDir
import scala.util.Random

class Staggerbot(name: String, body: RobotBody, randomSeed: Int) extends RobotBrain(name, body) {

  private val suunnat = Vector(CompassDir.North, CompassDir.East, CompassDir.South, CompassDir.West)
  private val generaattori = new Random(randomSeed)
  private def direction = suunnat(generaattori.nextInt(4))
  
  def moveBody(): Unit = {
    val tapahtuu = this.body.moveTowards(this.direction)
    if(tapahtuu) this.body.spinTowards(this.direction)
  }
  
  
}