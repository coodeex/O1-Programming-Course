package o1.legal

abstract class Entity(val name: String) {
  
  def contact: NaturalPerson
  
  def kind: String
  
  override def toString: String = name +  " (" + this.kind + ")"
}

