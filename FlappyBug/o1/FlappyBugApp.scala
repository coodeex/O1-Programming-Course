package o1

import constants._

// This class is introduced in Chapter 2.7.

object FlappyBugApp extends App {

  val sky        = rectangle(ViewWidth, ViewHeight,  LightBlue)
  val ground     = rectangle(ViewWidth, GroundDepth, SandyBrown)
  val trunk      = rectangle(30, 250, SaddleBrown)
  val foliage    = circle(200, ForestGreen)
  val tree       = trunk.onto(foliage, TopCenter, Center)
  val rootedTree = tree.onto(ground, BottomCenter, new Pos(ViewWidth / 2, 30))
  val scenery    = sky.place(rootedTree, BottomLeft, BottomLeft)


  val bugPic = Pic("ladybug.png")


  def rockPic(obstacle: Obstacle) = circle(obstacle.radius * 2, Black)


  // INSERT YOUR OWN CODE BELOW.
  val Peli = new Game
  
  val gui = new View(Peli, "FlappyBug") {
    
    var background = scenery
    
    def makePic = {
      
      var vaihe = Peli.obstacles.foldLeft(scenery)((valivaihe, este) => valivaihe.place(rockPic(este), este.pos))
      vaihe.place(bugPic, Peli.bug.pos)

      /*var valivaihe = scenery
      for(este <- Peli.obstacles){
        valivaihe = valivaihe.place(rockPic(este), este.pos)
      }
      valivaihe = valivaihe.place(bugPic, Peli.bug.pos)
      valivaihe*/
      
    }
    
    override def onKeyDown(painettu: Key) = {
      if (painettu == Key.Space) 
        Peli.activateBug() 
    }
    
    override def onTick() = {
      Peli.timePasses()
      this.background = this.background.shiftLeft(2)
    }
    
    override def isDone = Peli.isLost

    
  }
  
  gui.start()




}

