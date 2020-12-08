package o1.football2

import scala.collection.mutable.Buffer


/** The class `Match` represents match results in a football match statistics program.
  * A match is played between teams from two clubs: a home club and an away club.
  * Goals scored by players of either team can be added to the match object with the
  * method `addGoal`.
  *
  * The class is expected to be used so that a match object with no goals is initially
  * created as a real-life match starts. Goals are added incrementally as the match
  * progresses. (A match object has mutable state.)
  *
  * @param home  the club whose team plays at home in the match
  * @param away  the club whose team plays away in the match */
class Match(val home: Club, val away: Club) {

  private val homeScorers = Buffer[Player]()    // container: goalscorers of the home team are added here
  private val awayScorers = Buffer[Player]()    // container: goalscorers of the away team are added here

  private var kMaalit = 0
  private var vMaalit = 0
  

  def winnerName = {
    if (this.goalDifference < 0)
      this.away.name
    else if (this.goalDifference > 0)
      this.home.name
    else
      "no winner"
  }


  def addGoal(scorer: Player): Unit = {
    if (scorer.employer == this.home) {
      this.homeScorers += scorer
      kMaalit += 1
    }
    else{
      this.awayScorers += scorer
      vMaalit += 1
    }
  }

  def allScorers: Vector[Player] = (homeScorers ++ awayScorers).toVector
  def homeGoals: Int = kMaalit
  def awayGoals: Int = vMaalit
  def goalDifference: Int = (kMaalit - vMaalit)
  def hasScorer(possibleScorer: Player): Boolean = this.allScorers.contains(possibleScorer)
  def isHomeWin: Boolean = this.kMaalit > this.vMaalit
  def isAwayWin: Boolean = this.kMaalit < this.vMaalit
  def isGoalless: Boolean = this.totalGoals == 0
  def isHigherScoringThan(anotherMatch: Match): Boolean = this.totalGoals > anotherMatch.totalGoals
  def totalGoals: Int = this.kMaalit + this.vMaalit
  def isTied: Boolean = this.goalDifference == 0
  def location: String = this.home.stadium
  def winningScorerName: String = {
    if (this.isHomeWin)
      this.homeScorers(this.awayScorers.size).name
    else if (this.isAwayWin)
      this.awayScorers(this.homeScorers.size).name
    else
      "no winning goal"
  }
  
  override def toString = {
    val perusOsa = this.home.name + " vs. " + this.away.name + " at " + this.home.stadium + ": "
    if(isGoalless)
      perusOsa + "tied at nil-nil"
    else if (isTied)
      perusOsa + "tied at " + this.awayGoals +"-all"
    else if(this.isHomeWin)
      perusOsa + this.homeGoals + "-" + this.awayGoals + " to " + this.home.name
    else
      perusOsa + this.awayGoals + "-" + this.homeGoals + " to " + this.away.name
  }


}



