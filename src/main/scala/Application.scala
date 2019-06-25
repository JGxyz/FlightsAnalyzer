import org.json4s.DefaultFormats
import org.apache.log4j.Logger
import org.apache.log4j.Level
import java.util.Scanner

object Application {

  def printUsage(): Unit = {
    println("Program usage:")
    println("1 - show number of airports")
    println("2 - show all airports")
    println("3 - show number of routes")
    println("4 - show routes greater than n miles")
    println("5 - show routes smaller than n miles")
    println("6 - show n longest distance routes")
    println("7 - show distances for flights from src to dst")
    println("9 - exit the program")
  }

  def exit(): Unit = {
    println("Received 9, good bye :)")
    System.exit(0)
  }

  def readGraph(): AirportsGraph = {
    val fileName = "flights2018"
    val loader = DataLoader(fileName)
    val flights: Array[Flight] = loader.parseFile()
    val airportsGraph = AirportsGraph(flights)
    airportsGraph
  }

  def readAirport(airportsGraph: AirportsGraph): String = {
    println("Please input name of the airport:")
    val scanner = new Scanner(System.in)
    var airport = scanner.next()
    while (airportsGraph.checkVertex(airport).count() == 0) {
      println("Please input name of the correct airport, from the below list:")
      airportsGraph.showAllAirports()
      airport = scanner.next()
    }
    airport
  }

  def readMiles(): Double = {
    println("Please input the number of miles:")
    val scanner = new Scanner(System.in)
    var input = scanner.next()
    while (!checkIfaDouble(input) || input.toDouble <= 0) {
      println("Invalid input, try again, it should be a number greater than 0:")
      input = scanner.next()
    }
    input.toDouble
  }

  def readNumber(): Int = {
    println("Pleas input an integer number greater than 0:")
    val scanner = new Scanner(System.in)
    var input = scanner.next()
    while (!checkIfanInt(input) || input.toInt <= 0) {
      println("Invalid input, try again, it should be a number greater than 0:")
      input = scanner.next()
    }
    input.toInt
  }


  def handleRequest(graph: AirportsGraph, reqNumber: Int) = reqNumber match {
    case 1 => graph.countAllAirports()
    case 2 => graph.showAllAirports()
    case 3 => graph.countAllRoutes()
    case 4 => graph.routesGreaterThan(readMiles())
    case 5 => graph.routesSmallerThan(readMiles())
    case 6 => graph.nLongestDistanceRoutes(readNumber())
    case 7 => graph.timesFromSrcToDst(readAirport(graph), readAirport(graph))
    case 9 => exit()
    case _ => println("Unspecified request")
  }

  def checkIfanInt(number: String): Boolean = try {
    number.toInt
    true
  } catch {
    case _: NumberFormatException => false
  }

  def checkIfaDouble(number: String): Boolean = try {
    number.toDouble
    true
  } catch {
    case _: NumberFormatException => false
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val airportsGraph = readGraph()

    val scanner = new Scanner(System.in)

    while (true) {
      printUsage()
      val input = scanner.next()
      if (checkIfanInt(input))
        handleRequest(airportsGraph, input.toInt)
      else
        handleRequest(airportsGraph, -1)

    }
  }
}
