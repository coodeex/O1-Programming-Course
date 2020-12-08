package o1.election

import scala.collection.mutable.Buffer

class District(val name: String, val seats: Int, val candidates: Vector[Candidate]) {
  
  
  override def toString: String = {
    this.name + ": " + this.candidates.size + " candidates, " + this.seats + " seats"
  }
  
  def printCandidates(): Unit = this.candidates.foreach(kandi => println(kandi.toString))
    
  def candidatesFrom(party: String): Vector[Candidate]= {
    this.candidates.filter(_.party == party)
  }
  
  private def countVotes(candidates: Vector[Candidate]) = {
    var voteCount = 0
    for (candidate <- candidates) {
      voteCount += candidate.votes
    }
    voteCount
  }
  
  def candidatesByParty: Map[String, Vector[Candidate]] = this.candidates.groupBy(_.party)
  def topCandidatesByParty: Map[String, Candidate] = candidatesByParty.mapValues(_.maxBy(_.votes))
  def votesByParty: Map[String, Int] = candidatesByParty.mapValues(this.countVotes(_))
  def rankingsWithinParties: Map[String, Vector[Candidate]] = candidatesByParty.mapValues(_.sortBy(-_.votes))
  def rankingOfParties: Vector[String] = votesByParty.toVector.sortBy(-_._2).unzip._1
  def distributionFigures: Map[Candidate, Double] = {
      candidatesByParty.mapValues(_.sortBy(-_.votes)).unzip._2.toVector.flatten.zip(candidatesByParty.mapValues(_.sortBy(-_.votes)).map(data =>
      data._2.map(a => (votesByParty.get(data._1).get / (if((data._2.indexOf(a)+1) > 0){(data._2.indexOf(a)+1).toDouble}else{1.0})))).toVector.flatten).toMap}
  def electedCandidates = distributionFigures.toVector.sortBy(-_._2).unzip._1.take(seats)
  
  /*def a = candidatesByParty.mapValues(_.sortBy(-_.votes))
  def b = votesByParty.toVector.sortBy(-_._2).unzip._2
  def c = distributionFigures.toVector.sortBy(-_._2).unzip._1
  
  
  def ä = votesByParty.get("KOK")
  //kandidaatit
  def ö = candidatesByParty.mapValues(_.sortBy(-_.votes)).unzip._2.toVector.flatten
  //järjestysluku omassa vaalipiirissä
  def ö1 = candidatesByParty.mapValues(_.sortBy(-_.votes)).map(
      data => data._2.map(a => ( votesByParty.get(data._1).get / (if((data._2.indexOf(a)+1) > 0){(data._2.indexOf(a)+1).toDouble}else{1.0})))).toVector.flatten
  def ö2 = candidatesByParty.mapValues(_.sortBy(-_.votes)).mapValues(v => v.map(a=> v.indexOf(a)+1)).unzip._2.toVector.flatten
  def ö3 = ö.zip(ö1).toMap*/
  /** Returns the total number of votes received by the given candidates. */
  
  
  def topCandidate: Candidate = {
    candidates.maxBy(_.votes)
  }
  
  def totalVotes: Int = {
    this.candidates.foldLeft(0)(_ + _.votes)
  }
  
  def totalVotes(party: String): Int = {
    this.candidates.foldLeft(0)( (sum,kandi) => 
      (if(kandi.party==party){
        kandi.votes + sum
       }else{sum}))
  }
  
}