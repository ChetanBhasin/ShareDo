// Name of the project
name := "ShareDO"

version := "1.0"

// Set version of the scala being used
scalaVersion := "2.10.4"

// Add resolver for Typesafe repository for Akka toolkit
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

// Add Akka toolkit 2.3.6 as dependency
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.6"

// Add resolvers for smoke web server
resolvers += "mDialog releases" at "http://mdialog.github.com/releases/"

// Add smoke web server dependency for Akka 2.3.+
libraryDependencies += "com.mdialog" %% "smoke" % "2.1.0"

// Add simple web server dependencies
//libraryDependencies += "com.github.simplyscala" %% "simplyscala-server" % "0.5"

// Add dependency for the latest version of the swing if not available already
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

// Add SBT flags for use while compiling the program
scalacOptions ++= Seq("-deprecation", "-feature")
