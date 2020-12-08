package o1.carsim
import o1.Pos
import scala.math._

class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos) {

  private var menovetta = min(initialFuel, tankSize)
  private var paikka = initialLocation
  private var matkaMittari = 0.0
  

    
  def fuel(toBeAdded: Double): Double = {
    val add = min(toBeAdded, tankSize - this.menovetta)
    this.menovetta += add
    add
  }

  def fuel(): Double = {
    val add = tankSize - this.menovetta
    this.menovetta += add
    add
  }

  def location: Pos = this.paikka
  
  def fuelRatio: Double = this.menovetta / tankSize * 100                           

  def metersDriven: Double = matkaMittari                              

  def fuelRange: Double = menovetta / (fuelConsumption / 100) * 1000

  def drive(destination: Pos, metersToDestination: Double): Unit = {
    if (this.fuelRange >= metersToDestination){
      val kuluu = metersToDestination / 1000 * fuelConsumption / 100
      this.menovetta -= kuluu
      this.paikka = destination
      this.matkaMittari += metersToDestination
      
    }else{
      val siirtyma = this.fuelRange / metersToDestination // esim 0.5 -> 50%
      
      val kuluu = metersToDestination * siirtyma / 1000 * fuelConsumption / 100
      this.menovetta -= kuluu
      
      val ax = this.paikka.xDiff(destination) * siirtyma
      val yy = this.paikka.yDiff(destination) * siirtyma
      this.paikka = this.paikka.add(ax, yy)
      
      this.matkaMittari += metersToDestination * siirtyma
    }

  }
}