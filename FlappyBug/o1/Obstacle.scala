package o1

import constants._
import scala.math.hypot
import scala.util.Random

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int) {

  private var currentPos = this.randomLaunchPosition()
  
  def pos = this.currentPos
  
  def touches(bugi: Bug) = {
    this.currentPos.distance(bugi.pos) < BugRadius + this.radius
  }
  
  private def randomLaunchPosition() = {
    val launchX = ViewWidth + this.radius + Random.nextInt(500)
    val launchY = Random.nextInt(ViewHeight)
    new Pos(launchX, launchY)
  }
  
  def isActive = {
    if (this.currentPos.x + this.radius >= 0)
      true
    else
      false
  }
 
  def approach() = {
    if (this.isActive){
      this.currentPos = this.currentPos.addX(-ObstacleSpeed)
    }else{
      this.currentPos = this.randomLaunchPosition()
    }
    
    
  }
    

  override def toString = "center at " + this.pos + ", radius " + this.radius

}
