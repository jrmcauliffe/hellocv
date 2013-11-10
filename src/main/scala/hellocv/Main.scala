package hellocv

import org.opencv.core.{ Mat, MatOfByte, Size }
import org.opencv.imgproc.Imgproc
import org.opencv.highgui.Highgui
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import scala.swing.{ Panel, SimpleSwingApplication, MainFrame, Dimension, Graphics2D }
import scala.util.{ Try, Success, Failure }

object Main extends SimpleSwingApplication {
  System.loadLibrary("opencv_java247")
  var out: Mat = new Mat

  override def main(args: Array[String]) = {
    if (args.length == 1) {
      val in = Highgui.imread(args(0))
      if (!in.empty) {
        Imgproc.blur(in, out, new Size(16, 16))
        super.main(args)
      } else println("Could not load image " + args(0))
    } else {
      println("You must specify and image to process")
    }
  }

  def top() = new MainFrame {
    preferredSize = new Dimension(out.cols, out.rows)
    title = "Hello CV!"
    contents = new ImagePanel(out)
  }
}

// Custom panel for displaying opencv images
class ImagePanel(img: Mat) extends Panel {

  def toBuffImage(in: Mat) = {
    val matOfByte = new MatOfByte()
    Highgui.imencode(".jpg", in, matOfByte)
    val byteArray = matOfByte.toArray()
    var bufImage: BufferedImage = null
    try {
      val in = new ByteArrayInputStream(byteArray)
      bufImage = ImageIO.read(in)
    } catch {
      case e: Exception => {
        e.printStackTrace()
        println("Could not load image")
      }
    }
    bufImage
  }

  override def paintComponent(g: Graphics2D) = {
    val bufImage = toBuffImage(img)
    if (null != bufImage) g.drawImage(bufImage, 0, 0, null)
  }
}            