package netpro

import sys.process._
import java.net.URL
import java.io.File
import PortScanner._

object Grabber {

  // Method to download file from a URL after getting the filename from URL/filename in string format
  private def downloadFile(fromURL: String) = {
    lazy val filename = scala.io.Source.fromURL(fromURL + "/filename").mkString
    println("Got the file name, it is " + filename)
    println("Now downloading the file")
    val downloader = new URL(fromURL + "/getFile") #> new File(sys.env("HOME") + "/Downloads/" + filename)
    downloader.run()
  }

  def getFile = {

    def getFiles(url: String, port: Int): String = {
      val output = "http://" + url + ":" + port.toString
      downloadFile(output)
      output
    }

    scanAndRun(7771, getFiles)
  }

}