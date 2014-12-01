import AssemblyKeys._ // put this at the top of the file

assemblySettings

mainClass in assembly := Some("home.Entry")

// Name of the project
name := "ShareDO"

version := "1.0"

// Set version of the scala being used
scalaVersion := "2.11.0"

// Add dependency for the latest version of the swing if not available already
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

// Add SBT flags for use while compiling the program
scalacOptions ++= Seq("-deprecation", "-feature")

mainClass in (Compile, run) := Some("home.Entry")

mainClass in (Compile, packageBin) := Some("home.Entry")
