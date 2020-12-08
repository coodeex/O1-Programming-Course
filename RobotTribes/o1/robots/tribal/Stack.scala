package o1.robots.tribal


class Stack[Element] {

  private var alist = List[Element]()

  def peek = {
    if(this.alist.size == 0)None
    else Some(this.alist.head)
  }
  
  def pop() = {
    val eka = this.alist.headOption
    if(this.alist.size <= 1) this.alist = List[Element]()
    else this.alist = this.alist.tail
    eka
  }
  
  def push(uus: Element) = {
    this.alist = uus :: this.alist
  }
  
  def size = this.alist.size

  override def toString = "A stack of size " + this.size

}


