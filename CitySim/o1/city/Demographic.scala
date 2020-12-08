package o1.city

import o1.Color

sealed trait Demographic
// TODO: define trait Demographic and turn this into a type hierarchy

final class Occupied(val label: Color) extends Demographic {
  override def toString = s"occupied by the $label demographic"
}


object Vacant extends Demographic {
  override def toString = "vacant residence"
}

