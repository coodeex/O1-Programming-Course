package o1.adventure.ui

import o1.adventure._
import scala.io.StdIn._

/** The singleton object `AdventureTextUI` represents a fully text-based version of the
  * Adventure game application. The object serves as a possible entry point for the game,
  * and can be run to start up a user interface that operates in the text console.
  * @see [[AdventureGUI]] */
object AdventureTextUI extends App {

  private val game = new Adventure
  private val player = game.player
  this.run()


  /** Runs the game. First, a welcome message is printed, then the player gets the chance to
    * play any number of turns until the game is over, and finally a goodbye message is printed. */
  private def run() = {
    println(this.game.welcomeMessage)
    while (!this.game.isOver) {
      this.printAreaInfo()
      this.playTurn()
      this.game.jatkotAuki()
      this.game.bileetAuki()
      this.game.luentoAuki()
      this.game.tenttiAuki()
    }
    println("\n" + this.game.goodbyeMessage)
  }


  /** Prints out a description of the player character's current location, as seen by the character. */
  private def printAreaInfo() = {
    val area = this.player.location
    println("\n\n" + area.name)
    println("-" * area.name.length)
    println(area.fullDescription + "\n")
    
    println(this.player.nälkä)
    println(this.game.aika)
  }


  /** Requests a command from the player, plays a game turn accordingly, and prints out a report of what happened.  */
  private def playTurn() = {
    println()
    val command = readLine("Komento: ")
    val turnReport = this.game.playTurn(command)
    if (!turnReport.isEmpty) {
      println(turnReport)
    }
  }

}


