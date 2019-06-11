import org.json4s.DefaultFormats
import org.apache.log4j.Logger
import org.apache.log4j.Level
import java.util.Scanner

object Application{

  def printUsage(): Unit ={
    println("Program usage:")
    println("1 - show number of airports")
    println("2 - show all airports")
    println("3 - show number of routes")
    println("4 - show routes greater than 1000 miles")
    println("5 - show 10 longest distance routes")
  }

  def handleRequest(graph: AirportsGraph, reqNumber: Int) = reqNumber match {
    case 1 => graph.countAllAirports()
    case 2 => graph.showAllAirports()
    case 3 => graph.countAllRoutes()
    case 4 => graph.routesGreaterThan(1000)
    case 5 => graph.nLongestDistanceRoutes(10)
    case _ => println("Unspecified request")
  }

  def main(args: Array[String]): Unit ={
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    implicit val formats = DefaultFormats
    val fileName = "flights2018"
    val loader = DataLoader(fileName)
    val flights: Array[Flight] = loader.parseFile()

    val airportsGraph = AirportsGraph(flights)


    val scanner = new Scanner(System.in)

    printUsage()

    while(true){
        handleRequest(airportsGraph, scanner.nextInt())
    }
  }
}
