import Application.flights
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD

case class AirportsGraph(flights: Array[Flight]){
  val graph = {
    val conf = new SparkConf().setAppName("Airports").setMaster("local")
    val sc = new SparkContext(conf)
    val airports = flights.map(flight => flight.src).distinct.toSet.toSeq

    val airportsMap = (0 until airports.size)
      .map(i => airports(i) -> i.toLong)
      .toMap
    val routes = flights.map(flight => (airportsMap(flight.src), airportsMap(flight.dst)) -> flight.dist).distinct.toMap
    val edgesSet = routes.map(route => Edge(route._1._1, route._1._2, route._2)).toSet

    val vertices: RDD[(VertexId, String)] = sc.parallelize(airportsMap.toSeq.map(_.swap))
    val edges: RDD[Edge[Double]] = sc.parallelize(edgesSet.toSeq)
    val graph = Graph(vertices, edges)
    graph
  }

  def printVertices(): Unit ={
    graph.vertices.collect.foreach(println)
  }

  def printEdges(): Unit ={
    graph.edges.collect.foreach(println)
  }
}
