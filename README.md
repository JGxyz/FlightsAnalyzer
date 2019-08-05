## FlightAnalyser

To run this app you need Docker installed.
* Clone git repository `git clone https://github.com/JGxyz/FlightsAnalyzer.git`.
* Change repository `cd FlightsAnalyzer`.
* Type in the command line `docker build . -t scala-flights:0.1` in the **FlightsAnalyzer** folder. This will build image with prepared project and scala environment.
* Type in the command line `docker run -it scala-flights:0.1 bash` in **any** folder. This will create and log you into container with prepared created image.
* Type in the logged docker command line `sbt run` and test the program.
 
### Notes

scala-flights:0.1 it is just image name:tag, it can be replaced by any not existing image name, for example `scala` without tag will work as well.

If you want to clean:
* Image, type `docker rmi {image_id}`, where {image_id} can be found by typing `docker images -a`
* Container, type `docker rm {container_id}, where {container_id} can be found by typing `docker ps -a`