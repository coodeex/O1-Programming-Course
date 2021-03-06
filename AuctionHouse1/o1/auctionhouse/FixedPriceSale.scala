package o1.auctionhouse

class FixedPriceSale(val description: String, val price: Int, duration: Int) {
  
  private var jaljella = this.duration
  private var ostaja: Option[String] = None
  private var saatavilla: Boolean = true
  
  def daysLeft: Int = this.jaljella
  override def toString: String = this.description
  def buyer: Option[String] = this.ostaja
  def isExpired: Boolean = this.jaljella <= 0
  def isOpen = !this.isExpired && this.saatavilla
  def advanceOneDay(): Unit = {
    if (this.saatavilla && this.jaljella > 0)
      this.jaljella -= 1
      if(this.jaljella == 0)
        this.saatavilla = false
  }
  def buy(buyer: String): Boolean = {
    if (saatavilla){
      this.ostaja = Some(buyer)
      this.saatavilla = false
      true
    }else{
      false
    }
  }
  
  
}