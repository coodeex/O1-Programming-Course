package o1.people

class Passenger(val name: String, val card: Option[TravelCard]) {
  
  def canTravel = {
    this.card match{
      case Some(kortti) => 
        if(kortti.isValid){
          true
        }else{
          false
        }
      case None => false
    }
  }
  
  def hasCard = {
    this.card match{
      case Some(kortti) => true
      case None => false
    }
  }
  
}