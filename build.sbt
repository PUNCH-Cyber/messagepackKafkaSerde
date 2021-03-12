name := "kafka_serde"
version := "1.0.0"
organization := "com.punchcyber"
crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.3")

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
publishMavenStyle := true

publishTo := {
  val baseurl = "https://punchcyber.jfrog.io/artifactory/"
  if (isSnapshot.value)
    Some("snapshots" at baseurl + "mvn-dev-local;build.timestamp=" + new java.util.Date().getTime)
  else
    Some("releases" at baseurl + "mvn-prod-local")
}

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.5.1"  % Compile,
  "org.msgpack"      % "msgpack-core"  % "0.8.21" % Compile
)
