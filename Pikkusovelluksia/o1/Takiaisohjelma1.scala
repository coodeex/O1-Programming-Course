package o1


object Takiaisohjelma1 extends App {

  val takiainen = new Takiainen

  val leveys = 800
  val korkeus = 500
  val tausta = rectangle(leveys, korkeus, White)
  val takiaisenKuva = circle(40, YellowGreen)
  


  val nakyma = new View(takiainen, "settiä") {
      
    def makePic = {
      
      tausta.place(takiaisenKuva, takiainen.sijainti)
    }
  
    override def onMouseMove(kohta: Pos) = {
      takiainen.sijainti = kohta
    }
 
    
  }
  
  nakyma.start()
}

