# sparked-backend
Java spring backend for the new coda project





# Mongo & Docker
This project uses a mongo db for data persistence in a docker container for easy deployment. 
1. Install docker and run in linux mode.
2. Pull docker image with "docker pull mongo"
3. Run mongo in docker with "docker run --name coda-mongo-db -d -p 27017:27017 mongo

	Where: 
	- 27017 is the default mongodb port
	- coda-mongo-db is the name of this instance in docker

4. Install java-sdk (tested with 10.0.1)
	- test java nstallation and version with "java -version"
5. Install scoop (or similar, see https://gradle.org/install/ for details) with iex(new-object net.webclient).downloadstring("https://get.scoop.sh")
6. Install gradle with scoop install gradle
	- test gradle installation with "gradle"
7. Run "gradle tasks" to see available tasks. This may install missing dependencies.
8. Run "gradle wrapper" to create wrapper files.
9. Run "./gradlew build" to build java project. This may install missing dependencies.
	- you might have to set the %JAVA_HOME% path. Set to below bin folder. Restart powershell. A relog might be required.
10.

