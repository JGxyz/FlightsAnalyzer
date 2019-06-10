import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io.Source

case class DataLoader(fileName: String) {
  implicit val formats = DefaultFormats

  def parseLine(line: String): Flight = {
    val jsValue = parse(line)
    val flight = jsValue.extract[Flight]
    flight
  }

  def getFileSize(): Int = {
    val file = Source.fromFile(fileName)
    val size = file.getLines().size
    file.close()
    size
  }

  def parseFile(): Array[Flight] = {

    val file = Source.fromFile(fileName)

    val size = getFileSize()

    val flights = new Array[Flight](size)

    for ((line, i) <- file.getLines().zipWithIndex) {
        flights(i) = parseLine(line)
    }

    file.close()

    flights
  }
}
