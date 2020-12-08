package o1.items

// TODO: complete as instructed in Chapter 7.3.

class Container(name:String) extends Item(name) {

  private var montako = 0


  def addContent(newContent: Item): Unit = {
    montako += 1
  }

  override def toString() = {
    super.toString + " containing " + montako + " item(s)"
  }

}