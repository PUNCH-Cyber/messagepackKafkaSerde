name := "kafka_serde"
version := "1.0.0"
organization := "com.punchcyber"
crossScalaVersions := Seq("2.12.10", "2.13.3")

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
publishMavenStyle := true
publishTo := {
  val azureArtifacts =
    "https://pkgs.dev.azure.com/Punch-Research/2ad94760-8a08-4dea-b480-6ff857d18c06/_packaging/etl-utilities-release/maven/v1"
  if (isSnapshot.value)
    Some("snapshots" at azureArtifacts + "kafka-serde-snapshot/maven/v1")
  else
    Some("releases" at azureArtifacts + "kafka-serde-release/maven/v1")
}

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.5.1" % Compile,
  "org.msgpack" % "msgpack-core" % "0.8.21" % Compile
)
