import java.io._
import sys.process._

object updateJar extends App {

  val jarName =  if(args.nonEmpty) args(0) else "generic_email_sender.jar"
  val dockerName = jarName.takeWhile(c => c != '.')
  val dockerFileEntry = List("FROM anapsix/alpine-java",
                              s"COPY $jarName /home/$jarName",
                              s"ENTRYPOINT [${asp("java")}, ${asp("-jar")}, ${asp(s"/home/$jarName")}]",
                              s"RUN ls -l /home/$jarName")
  val commands = List(s"docker rmi $dockerName", s"docker build -t $dockerName ." )
  val file = "Dockerfile"
  val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))

  dockerFileEntry.foreach { x =>
    writer.write(x + "\n")
  }
  writer.write("\n")
  writer.close()
  commands.foreach(command => command!)
  new File("Dockerfile").delete()
  def asp(str: String): String = "\"" + str + "\""
}
