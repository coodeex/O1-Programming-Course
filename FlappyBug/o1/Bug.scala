package o1

import constants._

// Define class Bug here.
class Bug(private var currentPos: Pos) {
  
  private var yVelocity = 0.0
  
  val radius = BugRadius
  
  def pos = this.currentPos
  
  def move(paljo: Double) = this.currentPos = this.currentPos.addY(paljo).clampY(0,350)
  
  def flap(strength: Double) = {
    
    this.yVelocity = -strength
    //move(-this.yVelocity)
  }
  
  def fall() = {
    if (this.currentPos.y < 350.0)
      this.yVelocity += 2
      move(this.yVelocity)
  }
  
  def isInBounds = {
    this.currentPos.y > 0.01 && this.currentPos.y < 349.99
  }
  
  override def toString = "center at " + this.pos + ", radius " + this.radius
}
