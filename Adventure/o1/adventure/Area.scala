package o1.adventure

import scala.collection.mutable.Map
import scala.collection.mutable.Buffer

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String) {

  private val neighbors = Map[String, Area]()
  private var esineet = Buffer[Option[Item]]()
  
  var voiSyödä = false
  var voiTanssia = false
  var voiOstaaVirvokkeita = false
  var voiOstaaRuokaa = false
  var pääseeSisään = false         
  var luentoKäynnissä = false
  var tenttiKäynnissä = false
  
  
  def addItem(item: Item): Unit = {this.esineet += Some(item)}
  
  def contains(itemName: String): Boolean = {
    if(!this.esineet.isEmpty){
      this.esineet.exists(_.get.name == itemName)
    }else{false}
  }

  def removeItem(itemName: String): Option[Item] = {
    var poistettu:Option[Item] = None
    for(e <- esineet){
      if(this.contains(itemName)){
        if(e.get.name == itemName){
         poistettu = e
          esineet -= e
        }
      }
    }
    poistettu
  }

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)


  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }


  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction--area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. The return
    * value has the form "DESCRIPTION\n\nExits available: DIRECTIONS SEPARATED BY SPACES".
    * The directions are listed in an arbitrary order. */
  def fullDescription = {
    val exitList = "\n\nSuunnat joihin voit mennä: " + this.neighbors.keys.mkString(" ")
    
    val maassa: String = if(!this.esineet.isEmpty){
      "\nMaassa on: " + this.esineet.map(_.get.name).mkString +
      "\nVoit poimia komennolla \"poimi [esine]\""
    } else{""}
    
    val smökinMenu = if(voiOstaaVirvokkeita && voiOstaaRuokaa){
      """
        
        Huikkaa vain myyjälle mitä haluat, niin hän tarjoilee sinulle mielellään:
        
        hodari: 1,50e
        virvoke: 1,00e
      """
    }else ""
    val ruokaa: String = if(voiSyödä) "\n\nTäällä voi syödä lounaan edulliseen opiskelijahintaan.\n\nJos haluat syödä, niin sano \"syö\"." else ""
    val luento:String  = if(luentoKäynnissä) "\n\nLuento olisi nyt käynnissä. Jos tekee mieli mennä, niin sano vain että \"luennolle\" " else ""
    val tentti:String  = if(tenttiKäynnissä) "\n\nKello näyttäisi olevan sen verran, että tentti on käynnissä. Voit mennä sinne sanomalla itsellesi että \"tenttiin\". Onnea matkaan!" else ""
      
    this.description + maassa + ruokaa + luento + tentti + smökinMenu + exitList 
  }


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)



}
