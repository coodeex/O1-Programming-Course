package o1.adventure


/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as "go east" or "rest" */
class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "You go west."). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  
  def execute(actor: Player) = this.verb match {     
    case "mene"    => Some(actor.mene(this.modifiers), false)
    case "poimi"   => Some(actor.poimi(this.modifiers), false)
    case "tiputa"  => Some(actor.tiputa(this.modifiers), false)
       case "reppu"=> Some(actor.reppu, false)
       case "tutki"=> Some(actor.tutki(this.modifiers), false)
     case "lepää"  => Some(actor.lepää(), true)
    case "lopeta"  => Some(actor.lopeta(), false)
   case "aikataulu"=> Some(actor.aikataulu, false)
      case "syö"   => Some(actor.syö, false)
    case "lompakko"=> Some(actor.rahatilanne, false)
     case "virvoke"=> Some(actor.ostaVirvoke(), false)
     case "hodari" => Some(actor.ostaHodari(), false)
      case "tanssi"=> Some(actor.tanssi(), true)
   case "luennolle"=> Some(actor.luennolle(), false)
   case "tenttiin" => Some(actor.tenttiin(), false)
       case "apua" => Some(actor.apua, false)
       case "käytä"=> Some(actor.käytä(this.modifiers), false)
      case muu   => None
  }


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = this.verb + " (modifiers: " + this.modifiers + ")"


}

