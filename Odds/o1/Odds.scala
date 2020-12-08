package o1

// This class is gradually developed between Chapters 2.4 and 3.4.

class Odds(val wont: Int, val will: Int) {

  def probability = 1.0 * this.will / (this.wont + this.will)
  
  def isLikely = this.probability > 0.5
  
  def isLikelierThan(tapahtuma: Odds) = this.probability > tapahtuma.probability
  
  def moneyline = {
    if (!isLikely)
      100 * this.wont / this.will
    else
      -100 * this.will / this.wont
  }

  def fractional = this.wont + "/" + this.will
  
  def decimal = 1.0 / this.probability
  
  def winnings(panos: Double) = {
    panos * this.decimal
  }
  
  def not = new Odds(this.will, this.wont)
  
  override def toString = this.fractional
  
  def both(juttu: Odds) = {
    val ei = this.wont * juttu.wont + this.wont * juttu.will + this.will * juttu.wont
    val joo = this.will * juttu.will
    new Odds(ei, joo)
  }
  
  def either(juttu: Odds) = {
    val ei = this.wont * juttu.wont
    val joo = this.wont * juttu.will + this.will * juttu.wont + this.will * juttu.will
    new Odds(ei, joo)
  }

}
