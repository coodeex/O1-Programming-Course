package o1.football3

import scala.collection.mutable.Buffer
import scala.math.abs

class Season() {
  
  private val pelit = Buffer[Match]()
  
  def addResult(newResult: Match): Unit = {
    pelit += newResult
  }

  def latestMatch: Option[Match] = {
    
    if (this.pelit.isEmpty) {
      None
    } else {
      Some(pelit.last)
    }
  }
  
  
  def matchNumber(number: Int): Option[Match] = {
    val onkoPelei: Option[Match] = pelit.lift(number)
    onkoPelei match {
      case Some(jotai) => pelit.lift(number)
      case None => None
    }
  }
  
  def numberOfMatches: Int = this.pelit.size
  
  def biggestWin: Option[Match] = {
    if (this.pelit.isEmpty) {
      None
    } else {
      var isoinErotus = this.pelit(0)
      for (current <- this.pelit) {
        if (abs(current.goalDifference) > abs(isoinErotus.goalDifference)) {
          isoinErotus = current
        }
      }
      Some(isoinErotus)
    }
  }
}