package o1
import java.net.URL
import javax.sound.sampled._

object musaaa extends App {
  val url = new URL("https://www.kozco.com/tech/piano2.wav")
  val audioIn = AudioSystem.getAudioInputStream(url)
  val clip = AudioSystem.getClip
  clip.open(audioIn)
  clip.start
}