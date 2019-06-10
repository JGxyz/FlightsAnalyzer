import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.json4s.DefaultFormats

object Application extends App {
  implicit val formats = DefaultFormats
  val fileName = "/home/jolanta/cs/scala/FlightAnalyzer/src/main/resources/flights2018"
  val loader = DataLoader(fileName)
  val flights: Array[Flight] = loader.parseFile()
  for (i <- 0 to 100)
    flights(i).showSourceDest()

  //TODO CREATE A GRAPH ----> not here
  /*val conf = new SparkConf().setAppName("flights").setMaster("local")
  val sc = new SparkContext(conf)
  val airports = flights.map(flight => flight.src).distinct.toSet.toSeq

  val airportsMap = (0 until airports.size)
    .map(i => airports(i) -> i.toLong)
    .toMap
  val routes = flights.map(flight => (airportsMap(flight.src), airportsMap(flight.dst)) -> flight.dist).distinct.toMap
  val edgesSet = routes.map(route => Edge(route._1._1, route._1._2, route._2)).toSet

  val vertices = sc.parallelize(airportsMap.toSeq.map(_.swap))
  val edges = sc.parallelize(edgesSet.toSeq)
  val graph = Graph(vertices, edges)
  println(graph.vertices.take(2))
  println(graph.edges.take(2))*/
}
