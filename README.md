## FlightsAnalyzer  

To run the app you need Docker installed. Type following commands to run application.
* Clone git repository `git clone https://github.com/JGxyz/FlightsAnalyzer.git`.
* Change working repository `cd FlightsAnalyzer`.
* Type in the command line `docker build . -t scala-flights:0.1` in the **FlightsAnalyzer** folder. This will build image with prepared project and scala-sbt environment.
* Type in the command line `docker run -it scala-flights:0.1 bash` in **any** folder. This will create container based on prepared created image and log you into bash shell.
* Type command line in the logged docker bash shell `sbt run` and test the program.

### Notes

* You may skip 3-rd, and 4-th commands from above instruction and it may works, as long as you have scala/sbt installed on your system.

* Using Docker is optional, but it assure working across every platform without setting up environment as long as you have Docker installed.

* scala-flights:0.1 it is just image name:tag, it can be replaced by any not existing image name, for example `scala` without tag will work as well.

* If you want to clean:
	* Image - type `docker rmi {image_id}`, where {image_id} can be found by typing `docker images -a`
	* Container - type `docker rm {container_id}`, where {container_id} can be found by typing `docker ps -a`