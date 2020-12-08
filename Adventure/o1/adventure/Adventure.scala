package o1.adventure


/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "Fuksin selviytymispeli"

  private val metro       = new Area("Metroasema", "Kyltissä lukee Aalto-yliopisto. \nMiksiköhän täällä on niin paljon kerjäläisiä? Onhan tämä kuitenkin Suomen köyhin postinumero.")
  private val kandi       = new Area("Kandidaattikeskus", "Tänne kuulemma eksyy helposti. Kuitenkin tärkeä rakennus, joten kannattaa yrittää pärjätä!")
  private val alvari      = new Area("Alvari", "Aika pitkä jono, mutta saattaisi silti olla odottamisen arvoista syödä." +
                                     "\nRuokalista: \n  Kievin kanaa\n  Höyrytetty tumma riisi.")
  private val otakaari    = new Area("Otakaari", "Peeveli sentään kun tämä on niin pitkä tie. Näistä risteyksistä pitäisi sitten valita mihin seuraavaksi suuntaisi. Hmm..")
  private val täffä       = new Area("Täffä Lounasravintola", "Ovelle asti jatkuu jono, jossa kuuluu parin teinipojan keskustelua: \n\nMake: skrrt pist däbi toho jos säki otit megist messii" +
                                     "\nViljami: ofc bro [dab] meinasiks korkkaa jo jonos? \nMake: jea lit spagenttibileet käyntii!" )
  private val jmt         = new Area("Jämeräntaival", "Olisipa mukava asua täällä. Harmi vain, että AYY:n jonot ovat niin pitkät. ")
  private val smt         = new Area("Servin Maijan tie", "Kaikki talot näyttää samalta. Tänne ei ilman kutsua kannata tulla")
  private val smökki      = new Area("Servin mökki", "Ovessa lukee, että tänään on himo hyvät fuksibileet klo 21.00 alkaen kantsii tulla nopee :D")
  private val taikametsä  = new Area("Taikametsä", "Tämän pimeän ja salaperäisen metsän maagisuus piilee siinä, kuinka nopeasti se viekään Servin Maijan tieltä Servin mökille tai päinvastoin.")
  private val jatkot      = new Area("Jatkot", "Ei mitään tietoa kenen kämpässä ollaan, mutta ihmisillä vaikuttaa olevan hauskaa. ")
  private val destination = jatkot

        metro.setNeighbors(Vector(                        "oik" -> kandi                                                 ))
        kandi.setNeighbors(Vector("ylös" -> kandi,       "oik" -> otakaari,   "alas" -> alvari,      "vas" -> metro   ))
       alvari.setNeighbors(Vector("ylös" -> kandi                                                                        ))
     otakaari.setNeighbors(Vector("ylös" -> smt,         "oik" -> jmt,        "alas" -> täffä,       "vas" -> kandi   ))
        täffä.setNeighbors(Vector("ylös" -> otakaari                                                                     ))
          jmt.setNeighbors(Vector("ylös" -> smökki,                                                    "vas" -> otakaari))
          smt.setNeighbors(Vector(                        "oik" -> taikametsä, "alas" -> otakaari                       ))
       smökki.setNeighbors(Vector("ylös" -> taikametsä,                        "alas" -> jmt                            ))
   taikametsä.setNeighbors(Vector(                                              "alas" -> smökki,      "vas" -> smt     ))

  // TODO: place these two items in clearing and southForest, respectively
  taikametsä.addItem(new Item("sisätossut", "Siniharmaat sisätossut, jotka ovat sinulle juuri sopivan kokoiset. \nMelko hyväkuntoiset siihen nähden, että ne löytyivät metsästä."))
  //southForest.addItem(new Item("remote", "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery."))

   alvari.voiSyödä = true
   täffä.voiSyödä = true
   smökki.voiTanssia = true
   smökki.voiOstaaVirvokkeita = true
   
  /** The character that the player controls in the game. */
  val player = new Player(this, metro)

  /** Päivä alkaa */
  var kello = 8
  /** Kello viisi aamulla peli loppuu */
  val timeLimit = 5 

  def jatkotAuki() = if(this.player.location == smökki && smökki.pääseeSisään) smt.setNeighbor("ylös", jatkot)
  
  def bileetAuki() = {
    if(kello == 21){
      smökki.description = """Tervetuloa Smökin fuksibileisiin! Täällä voi mennä tanssilattialle näyttämään taitoja. Lisäksi virvokkeita on tarjolla. Pidä hauskaa!
        
        Jos mieli tekee tanssia, niin kannusta itseäsi siihen toteamalla, että "tanssi"
        
        Tervetuloa myös jatkoille! Ne ovat osoitteessa Smt 10 Ö23"""
      smökki.pääseeSisään = true
      smökki.voiTanssia = true
      smökki.voiOstaaVirvokkeita = true
      smökki.voiOstaaRuokaa = true
    }
      
  }
  
  def luentoAuki() = {
    if(kello == 10) kandi.luentoKäynnissä = true
    else if (kello == 12) kandi.luentoKäynnissä = false
  }
  
  def tenttiAuki() = {
    if(kello == 16) kandi.tenttiKäynnissä = true
    else if(kello >= 18) kandi.tenttiKäynnissä = false
  }
  
  def aika = "Kello on " + this.kello + ".00"

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.destination

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.kello == this.timeLimit || this.player.nälkäMittari == 9

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = ("Heipähei fuksi ja tervetuloa Otaniemeen! Kuten yleensäkin, myös tänään, sinulla on melko kiireinen päivä tiedossa. \nUskon silti, että selviät kunnialla kouluhommista ja muistat myös pitää himo hauskan päivän!\n"
  + "Päivän aikataulu:\n" + this.player.aikataulu + "\nLompakossasi on " + this.player.lompakko + " euroa.\n\nApua saat pyytämällä \"apua\".")


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete && this.player.tenttiarvosana && this.player.jalassa)
      "Sinä teit sen! Pääsit läpi tentistä ja muistit pitää hauskaa. Niin sitä pitää ;) Kaveri sanoi, että voit nukkua tuossa sohvalla," + 
      "\njoten ei muuta kun hyvää yötä. Ainiin pakko myöntää, että on kyllä päheet sisätossut sulla."
      
    else if (this.isComplete && this.player.tenttiarvosana)
      "Sinä teit sen! Pääsit läpi tentistä ja muistit pitää hauskaa. Niin sitä pitää ;) Kaveri sanoi, että voit nukkua tuossa sohvalla, joten ei muuta kun hyvää yötä."
    else if (this.isComplete)
      "Muuten hyvä päivä, mutta tentti ei mennyt läpi. Sinulla taisi kuitenkin olla hauskaa. Kaveri sanoi, että voit nukkua tuossa sohvalla, joten ei muuta kun hyvää yötä."
    else if (this.kello == this.timeLimit)
      "Julkinen liikenne ei enää kulje ja ihmisiä ei näy missään. Jäit yksin loukkuun otaniemeen. Ei tässä näin pitänyt käydä.."
    else if (this.player.nälkäMittari == 9)
        "Nyt ei jaksa mennä enää pidemmälle. Olisit syönyt ajoissa. Ruoka ei kumminkaan ole niin kovin kallista täällä Otaniemessä."
    else  // game over due to player quitting
      "Luovuttaja!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    outcomeReport match {      
      case Some(sisältö) => {
        if(sisältö._2){
          this.player.nälkäMittari += 1
          this.kello += 1
          this.kello = this.kello % 24
        }
      sisältö._1}
        
      case eiOleKomento => "Tuntematon komento: \"" + command + "\". Apua saat pyytämällä \"apua\"."
    }
  }


}

