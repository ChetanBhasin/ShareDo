package netpro

import scala.swing._

import sys.process._
import java.net.URL
import java.io.File
import PortScanner._

object Grabber {

  // Method to download file from a URL after getting the filename from URL/filename in string format
  private def downloadFile(getURL: String, lable: Label) = {
    lazy val filename = scala.io.Source.fromURL(getURL + "/filename").mkString
    println("Downloading: " + filename)
    val downloader = new URL(getURL + "/getFile") #> new File(sys.env("HOME") + "/Downloads/" + filename)
    downloader.run
    lable.text = "Download finished"
  }

  def getFile(lable: Label) = {

    def getFiles(url: String, port: Int): String = {
      val output = "http://" + url + ":" + port.toString
      downloadFile(output, lable)
      output
    }

    scanAndRun(7771, getFiles)
  }

}