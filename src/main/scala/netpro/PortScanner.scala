package netpro

import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._
import scala.util.Try
import scala.concurrent._
import java.util.concurrent.Executors

object PortScanner {

  // Function that would scan and return all the open nodes on the network
  private def scanNodes(port: Int) = {
    // Define an execution context such that you don't mess up the concurrency model
    implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(230))
    // Main value that stores the result from Future.traverse
    val result = Future.traverse(1 to 227) {
      ipEnd =>
        Future {
          Try {
            val socket = new java.net.Socket()
            val currentIP = "192.168.1." + ipEnd
            socket.connect(new java.net.InetSocketAddress(currentIP, port), 200)
            socket.close()
            println("Returning the scanned IP" + currentIP)
            currentIP
          }.toOption
        }
    }

    // Returns a vector of all available ports asynchronously
    Try {
      Await.result(result, 10 seconds)
    }.toOption.getOrElse(Nil).flatten
  }

  // Main function that would scan all the nodes and then run a callback on them
  def scanAndRun(port: Int, callback: (String, Int) => String) = {
    scanNodes(port).map {
      case ip: String => callback(ip, port)
      case _ => Nil
    }
  }

}