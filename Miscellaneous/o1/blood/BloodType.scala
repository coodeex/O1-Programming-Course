package o1.blood


class BloodType(val abo: String, val rhesus: Boolean) {


  override def toString = {
    val plusVaiMiinus = if(rhesus) "+" else "-"
    this.abo + plusVaiMiinus
  }


  def hasSafeABOFor(recipient: BloodType) = {
    this.abo == "O" || this.abo == recipient.abo || recipient.abo == "AB"
  }


  def hasSafeRhesusFor(recipient: BloodType) = {
    this.rhesus == recipient.rhesus || this.rhesus && recipient.rhesus || recipient.rhesus
  }


  def canDonateTo(recipient: BloodType) = {
    this.hasSafeABOFor(recipient) && this.hasSafeRhesusFor(recipient)
  }


  def canReceiveFrom(donor: BloodType) = {
    donor.hasSafeABOFor(this) && donor.hasSafeRhesusFor(this)
  }


}

