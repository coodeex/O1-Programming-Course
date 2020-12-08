package o1.people

class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]) {

  def isAlive = {
    this.yearOfDeath match{
      case Some(jotai) => false
      case None => true
    }
  }
  
  override def toString = {
    if (this.isAlive){
      this.name + "(" + this.yearOfBirth + "-)"
    }else{
      val kuoli = this.yearOfDeath match{
        case Some(vuosi) => vuosi
        case None => "fail"
      }  
      this.name + "(" + this.yearOfBirth + "-" + kuoli + ")"
      
    }
  }

}
