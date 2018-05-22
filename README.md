# sparked-backend
Java spring backend for the new coda project


# Git
This project uses github for code version control. 
1. Install git
2. Pull project with "git pull https://github.com/rredbird/sparked-backend.git"
	- On workmachine the cache only works in admin mode. Use "start powershell -Verb runAs" to start a new powershell window in admin mode. Or use <WIN> + X , A


# Mongo & Docker
This project uses a mongo db for data persistence in a docker container for easy deployment. 
1. Install docker and run in linux mode.
2. Pull docker image with "docker pull mongo"
3. Run mongo in docker with "docker run --name coda-mongo-db -d -p 27017:27017 mongo

	Where: 
	- 27017 is the default mongodb port
	- coda-mongo-db is the name of this instance in docker

# Java & Gradle
1. Install java-sdk (tested with 10.0.1)
	- test java nstallation and version with "java -version"
2. Install scoop (or similar, see https://gradle.org/install/ for details) with iex(new-object net.webclient).downloadstring("https://get.scoop.sh")
3. Install gradle with scoop install gradle
	- test gradle installation with "gradle"
4. Run "gradle tasks" to see available tasks. This may install missing dependencies.
5. Run "gradle wrapper" to create wrapper files.
6. Run "./gradlew build" to build java project. This may install missing dependencies.
	- you might have to set the %JAVA_HOME% path. Set to below bin folder. Restart powershell. A relog might be required.

# Start project

Start project with "./gradlew run"

