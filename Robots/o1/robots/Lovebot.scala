package o1.robots

import o1._
import scala.math.abs

class Lovebot(name: String, body: RobotBody, val beloved: RobotBody) extends RobotBrain(name, body) {

  
  private def xDistance = abs(this.body.location.xDiff(beloved.location))
  private def yDistance = abs(this.body.location.yDiff(beloved.location))
  
  def moveBody(): Unit = {
    if(this.xDistance + this.yDistance > 1){ // diagonaali ei kelpaa
      if(this.xDistance >= this.yDistance){
        this.body.moveTowards(this.location.xDirectionOf(beloved.location).get)
      }else{
        this.body.moveTowards(this.location.yDirectionOf(beloved.location).get)
      }
    }
  }
}
