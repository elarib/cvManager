name := "just-play-scala"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
	"com.typesafe.slick" %% "slick" % "3.1.1",
	"com.typesafe.slick" %% "slick-codegen" % "3.1.1",
	"mysql" % "mysql-connector-java" % "5.1.36",
	"org.webjars" %% "webjars-play" % "2.5.0",
	"net.codingwell" %% "scala-guice" % "4.0.1",
	"org.mindrot" % "jbcrypt" % "0.3m",
	"org.slf4j" % "slf4j-nop" % "1.6.4",
	"com.typesafe.play" %% "play-mailer" % "5.0.0",
	"com.pauldijou" %% "jwt-play" % "0.8.0",
	"com.pauldijou" %% "jwt-play-json" % "0.8.0" ,
	"io.spray" %%  "spray-json" % "1.3.2",
	specs2 % Test,
	cache,
	filters
)
