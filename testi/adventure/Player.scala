package o1.adventure

import scala.collection.mutable.Map


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var reppu = Map[String, Item]()


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  def has(itemName: String): Boolean = this.reppu.contains(itemName)

  /** Returns the current location of the player. */
  def location = this.currentLocation
  
  def inventory = { 
    if(!this.reppu.isEmpty){
      var a = ""
      for(i<-reppu.values){
        a += "\n" + i.name
      }
      "You are carrying:" +
      a
    }else{
      "You are empty-handed."
    }
  }
  
  def examine(itemName: String) = {
    if(this.reppu.contains(itemName)){
      
      
      "You look closely at the " + itemName + ".\n" + this.reppu.get(itemName).get.description
      
    }else{
      "If you want to examine something, you need to pick it up first."
    }
  }
  
  def drop(itemName: String): String = {
    val i = this.reppu.get(itemName)
    if(this.reppu.contains(itemName)){
      this.reppu -= itemName
      this.location.addItem(i.get)
      "You drop the " + itemName + "."
    }else{
      "You don't have that!"
    }
  }

  def get(itemName: String): String = {
    if(this.location.contains(itemName)){
      this.reppu += itemName -> this.location.removeItem(itemName).get
      "You pick up the " + itemName + "."
    }else{
      "There is no " + itemName + " here to pick up."
    }
  }

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) "You go " + direction + "." else "You can't go " + direction + "."
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = {
    "You rest for a while. Better get a move on, though."
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name


}

