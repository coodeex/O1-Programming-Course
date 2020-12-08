package o1.robots

import o1._

class Psychobot(name: String, body: RobotBody) extends RobotBrain(name, body) {

  private val suunnat = Vector(CompassDir.North, CompassDir.East, CompassDir.South, CompassDir.West)
  
  
  private var tuolla = Buffer[CompassDir]()
  def scan(suunta: CompassDir) = {
    val virta = this.location.pathTowards(suunta).map(this.body.world.elementAt(_))
    .dropWhile(_.isEmpty).takeWhile(!_.isUnpassable)
    if(virta.isEmpty || virta.map(_.robot).flatten.dropWhile(_.isBroken).isEmpty){
      None
    }else if (!virta.map(_.robot).flatten.head.isBroken){
      tuolla = tuolla :+ suunta
      Some(virta.map(_.robot).flatten.dropWhile(_.isBroken).head)
    }else{None}
  }
    
  def moveBody() {
    var uhrilista = Buffer[Option[RobotBody]]()
    for (dir <- suunnat){
      uhrilista = uhrilista :+ scan(dir)
    }
    
    val uhri = {
      if (uhrilista.forall(_ == None)){
        None
      }else{uhrilista.dropWhile(_.isEmpty).head}
    }
    
    uhri match {
      case Some(joku) => {
        this.body.spinTowards(tuolla.head)
        while(this.body.location.distance(joku.location) > 1){
          this.body.moveTowards(tuolla.head)
        }
        this.body.moveTowards(tuolla.head)
      }
      case None => None
    }
    tuolla = Buffer[CompassDir]()
  }
}
