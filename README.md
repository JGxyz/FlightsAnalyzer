To run this app you need Docker installed.
* Type in the command line `docker build . -t scala-flights:1.0` in the **FlightsAnalyzer** folder . This will build image with whole project.
* Type in the command line `docker run -it scala-flights:1.0 bash` in **any** folder. This will create and log you into container with prepared project and scala environment.
* Type in the logged docker command line `sbt run` and test the program.

Note that scala-flights:1.0 it is just image name:tag, it can be replaced by any not existing image, for example `scala` without tag will work as well. 