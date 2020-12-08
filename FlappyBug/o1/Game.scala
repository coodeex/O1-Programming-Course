package o1

import constants._

class Game {

    val bug = new Bug(new Pos(100, 40))
    val obstacles = Vector(new Obstacle(70), new Obstacle(30), new Obstacle(20))
    
    def timePasses() = {
      this.bug.fall()
      this.obstacles.foreach(este => este.approach())
      /*for(este <- this.obstacles){
        este.approach()
      }*/
    }
    
    def isLost = {
      
      obstacles.exists( _.touches(this.bug)) || !this.bug.isInBounds
      /*var kusi = false
      for(este <- obstacles){
        if(este.touches(this.bug)){
          kusi = true
        }
      } 
      kusi || !this.bug.isInBounds*/
    }
    
    def activateBug() = this.bug.flap(FlapStrenght) 


}


  // Your code goes here. Please add only what is requested by the ebook. To avoid
  // confusing our automatic assessment system, please don't invent additions of your own
  // here (at least not until you're done with the ebook’s official FlappyBug assignments).