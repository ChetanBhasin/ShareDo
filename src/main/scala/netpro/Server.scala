package netpro

import smoke._
import com.typesafe.config.ConfigFactory

class Server(file: java.io.File) extends Smoke {

  println("Server started at 7771 with " + file.getName)

  val smokeConfig = ConfigFactory.load().getConfig("smoke")
  val executionContext = scala.concurrent.ExecutionContext.global

  onRequest {
    case GET(Path("/filename")) => reply {
      Response(Ok, body = file.getName)
    }
    case GET(Path("/getFile")) => reply {
      val in = new java.io.FileInputStream(file)
      val bytes = new Array[Byte](file.length.toInt)
      in.read(bytes)
      in.close()
      Response(Ok, body = RawData(bytes))
    }
    case _ => reply {
      Response(Ok, body = "This is the ShareDO server, please don't use this port for other purposes")
    }
  }

  after { response ⇒
    val headers = response.headers ++ Seq(
      "Server" -> "ShareDO Server",
      "Connection" -> "Close")
    Response(response.status, headers, response.body)
  }

}