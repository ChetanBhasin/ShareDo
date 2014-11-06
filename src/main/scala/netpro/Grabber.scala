package netpro

import scala.swing._

import sys.process._
import java.net.URL
import java.io.File
import PortScanner._

object Grabber {

  private def fullDownloader(getURL: String, lable: Label) = {
    lazy val filename = scala.io.Source.fromURL(getURL + "/filename").mkString
    println("Downloading file: " + filename)
    try {
      val downloader = new URL(getURL + "/getFile") #> new File(sys.env("HOME") + "/Downloads/" +filename)
      downloader.run
    } finally {
      lable.text = "Download finished"
    }
  }

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
      fullDownloader(output, lable)
      output
    }

    scanAndRun(7771, getFiles)
  }

}