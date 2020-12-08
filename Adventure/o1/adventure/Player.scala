package o1.adventure

import scala.collection.mutable.Map


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(val peli: Adventure, startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var reppuu = Map[String, Item]()
  var lompakko = 5.13 //euroa
  var nälkäMittari = 0
  var tenttiarvosana = false  //false hylätty ja true hyväksytty
  var jalassa = false


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  def has(itemName: String): Boolean = this.reppuu.contains(itemName)
  
  def apua = {
    """
      Käytettävissä olevat komennot ovat:
      "mene [suunta]" Suunnat ovat oik, vas, ylös ja alas.(Kartalla).
      "aikataulu" Voit tarkastaa päivän aikataulun.
      "lompakko" Voit tarkistaa rahatilanteesi.
      "tanssi" Aina on mahdollista tanssia.
      "lepää" Voit ottaa hetken rennosti.
      "syö" Jos paikassa jossa olet, on tarjolla ruokaa, voit syödä.
      "poimi [esine]" Jos maassa on esine sen voi poimia.
      "käytä" [esine]" Jos hallussasi on esine voit käyttää sitä.
      "reppu" Voit katsoa, mitä repustasi löytyy.
      "tutki [esine]" Voit tutkia esinettä, joka löytyy repustasi.
      "tiputa [esine]" Voit tiputtaa esineen, joka sinulla on mukanasi.
      "virvoke" Voit ostaa virvokkeen, jos niitä on myynnissä.
      "hodari" Voit ostaa hodarin, jos niitä on myynnissä.
      "luennolle" Jos luento on käynnissä, niin tällä komennolla voit mennä sinne.
      "tenttiin" Jos olet ajoissa tenttipaikalla voit mennä tenttiin.
      "lopeta" Voit lopettaa pelin.
      "apua" Voit aina pyytää apua, jos et muista yllä olevia komentoja.
    """
  }
  
  def käytä(esine: String) = {
    if(this.reppuu.contains("sisätossut") && esine == "sisätossut"){
      this.jalassa = true
      this.reppuu -= "sisätossut"
      "Vedät jalkaan upeat sisätossut."
    }else "Et voi käyttää esinettä, jota sinulla ei ole mukanasi."
  }
  
  def luennolle() = {
    if(this.currentLocation.luentoKäynnissä){
      this.peli.kello += 12 - this.peli.kello
      this.nälkäMittari += 12 - this.peli.kello
      """
        Nobody:
        
        Malinen: On olemassa joukko lehmiä ja susia. Määritelmän mukaan jokaisen joukon osajoukko on 0.
        Ovatko tällöin susien ja lehmien tyhjät osajoukot samaa osajoukkoa?"""
    }
    else"Ei täällä ole luentoa käynnissä... Olethan varmasti oikeassa paikassa oikeaan aikaan?"
  }
  
  def tenttiin() = {
    if(this.currentLocation.tenttiKäynnissä){
      this.nälkäMittari += 18 - this.peli.kello
      this.peli.kello += 18 - this.peli.kello
      println("2 + 2 - 1?")
      val command = readLine("Command: ")
      this.tenttiarvosana = command == 3.toString
      "\nToivotaan parasta, että koe meni hyvin"
     }else{
      "Nyt taidat olla väärässä paikassa, jos tenttiin oli aikomus mennä.."
    }
  }
  def syö() = {
    if(this.currentLocation.voiSyödä && this.lompakko >= 2.60){
      this.lompakko -= 2.60
      this.nälkäMittari = 0
      this.peli.kello += 1
      this.peli.kello = this.peli.kello % 24
      "Kyllä nyt lähti nälkä"
    }else if(this.lompakko < 2.60) "Sinulla ei ole tarpeeksi rahaa."
    else {
      "Täällä ei voi syödä. Jos on nälkä, niin kannattaa etsiä jokin ravintola."
    }
  }
  
  def tanssi() = {
    if(this.currentLocation.voiTanssia)"Kaikki ympärillä olevat ihmiset oikein hämmästelevät tanssiliikkeitäsi. Wow!"
    else if(this.currentLocation.voiSyödä)"Koita käyttäytyä XD Ihmiset tuijottavat sinua ja hihittävät kun tanssit keskellä ruokalaa."
    else "Siistit tanssiliikkeet, mutta harmi vain, että kukaan ei kiinnitä sinuun mitään huomiota. Ehkä vähän huono paikka tanssimiselle?"
  }
  
  def ostaVirvoke() = {
    if(this.currentLocation.voiOstaaVirvokkeita && this.lompakko >= 1){
      this.lompakko -= 1
      this.nälkäMittari -= 1
      "Ai että olipas hyvä juoma"
    }else if(this.lompakko < 1) "Sinulla ei ole tarpeeksi rahaa."
    else {
      "Täällä ei myydä virvokkeita."
    }
  }
  
  def ostaHodari() = {
    if(this.currentLocation.voiOstaaRuokaa && this.lompakko >= 1.5){
      this.lompakko -= 1.5
      this.nälkäMittari -= 4
      "Ai että olipas herkullinen nakkisämpylä"
    }else if(this.lompakko < 1.5) "Sinulla ei ole tarpeeksi rahaa."
    else {
      "Täällä ei ole kuumia koiria myynnissä."
    }
  }
  
  //BigDecimal pyöristää luvun aina kahteen desimaaliin, koska scalan luvut välillä temppuilevat
  def rahatilanne = "Lompakossa on " + BigDecimal(lompakko).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble + " euroa."
  
  def nälkä:String = {
    if(nälkäMittari >= 5){
      if(nälkäMittari == 5)"Vähän alkaa vatsa kurnimaan."
      else if(nälkäMittari == 6)"Kohta pitäisi varmaan mennä syömään."
      else if(nälkäMittari == 7)"Nyt on jo tosi kova nälkä."
      else if(nälkäMittari == 8)"Jos ei heti saa murua rinnan alle, niin voimat loppuu."
      else ""
    }
    else ""
  }

  /** Returns the current location of the player. */
  def location = this.currentLocation
  
  def reppu = { 
    if(!this.reppuu.isEmpty){
      var a = ""
      for(i<-reppuu.values){
        a += "\n" + i.name
      }
      "Sinulla on repussasi:" +
      a
    }else{
      "Reppusi on tyhjä."
    }
  }
  
  def tutki(itemName: String) = {
    if(this.reppuu.contains(itemName)){
      "Lähemmässä tarkastelussasi on \"" + itemName + "\".\n" + this.reppuu.get(itemName).get.description
    }else{
      "Et voi tutkia esinettä, jota sinulla ei ole."
    }
  }
  
  def tiputa(itemName: String): String = {
    val i = this.reppuu.get(itemName)
    if(this.reppuu.contains(itemName)){
      this.reppuu -= itemName
      this.location.addItem(i.get)
      "Hei! Sinulta taisi pudota " + itemName + "."
    }else{
      "Et voi pudottaa esinettä, jota sinulla ei ole."
    }
  }

  def poimi(itemName: String): String = {
    if(this.location.contains(itemName)){
      this.reppuu += itemName -> this.location.removeItem(itemName).get
      "Sinä nappasit haltuusi \"" + itemName + "\"."
    }else{
      "Ei näy sellaista esinettä/asiaa kuin \"" + itemName + "\", jonka voisit poimia."
    }
  }
  
  def aikataulu = {
    """
      MS-A0103 - Differentiaali- ja integraalilaskenta 1 luento klo 10.00 - 12.00 paikassa Kandidaattikeskus D-sali (Ei läsnäolopakkoa)
      
      MS-A0103 - Differentiaali- ja integraalilaskenta 1 tentti klo 16.00 - 18.00 paikassa Kandidaattikeskus A-sali (pakollinen, jotta pääsee kurssista läpi)
      
      Fuksibileet Smökissä klo 21.00
      """
   
  }

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def mene(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined){ 
        this.nälkäMittari += 1
        this.peli.kello += 1
        this.peli.kello = this.peli.kello % 24
        "Menet suuntaan " + direction + "."
      }else{
        "Et voi mennä suuntaan " + direction + "."
      }
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def lepää() = {
    "Otat hetken rennosti. Älä jää kuitenkaan siihen loppuelämäksesi toimittamaan tyhjää."
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def lopeta() = {
    this.quitCommandGiven = true
    ""
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Olet paikassa: " + this.location.name


}


