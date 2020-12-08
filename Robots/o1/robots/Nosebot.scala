package o1.robots

import o1._

class Nosebot(name: String, body: RobotBody) extends RobotBrain(name, body) {
  
  def attemptMove():Boolean = {
    if(super.squareInFront.isEmpty) {
      super.moveCarefully()
    }else{
      this.body.spinClockwise()
      false
    }
  }

  
  def moveBody() = {
    var ni = true
    var kulma = 0

    while(ni && kulma < 360 ){
      ni = !super.squareInFront.isEmpty
      kulma += 90
      this.attemptMove()
      
    }
  }
}
