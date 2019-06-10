import sbt.Keys.libraryDependencies

name := "FlightAnalyzer"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.5.1"

libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.4.3"