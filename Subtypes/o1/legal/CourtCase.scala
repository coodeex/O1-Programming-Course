package o1.legal

class CourtCase(val plaintiff: Entity, val defendant: Entity){
  override def toString: String = plaintiff.name + " v. " + defendant.name
}
// TODO: define class CourtCase

