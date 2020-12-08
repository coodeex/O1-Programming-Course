package o1.shapes

import scala.math._

class RightTriangle(val kanta: Double, val korkeus: Double) extends Shape {
    
  def area = this.kanta * this.korkeus / 2
  
  def hypotenuse = sqrt(pow(this.kanta,2) + pow(this.korkeus, 2))
  
  def perimeter = this.kanta + this.korkeus + this.hypotenuse
}
