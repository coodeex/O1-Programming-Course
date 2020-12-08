package paketti
import java.io._
import javax.sound.sampled._


object musa extends App {
  val a = paketti.musa
  
  val biisi = new File("CrabRave")
  
  val audioIn = AudioSystem.getAudioInputStream(biisi)
  val clip = AudioSystem.getClip
  clip.open(audioIn)
  clip.start
}