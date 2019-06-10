import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.json4s.DefaultFormats
import org.apache.log4j.Logger
import org.apache.log4j.Level

object Application extends App {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  implicit val formats = DefaultFormats
  val fileName = "flights2018"
  val loader = DataLoader(fileName)
  val flights: Array[Flight] = loader.parseFile()
  /*for (i <- 0 to 100)
    flights(i).showSourceDest()*/

  val airportsGraph = AirportsGraph(flights)

  airportsGraph.printVertices()
  airportsGraph.printEdges()
}
