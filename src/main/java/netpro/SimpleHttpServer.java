package netpro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

public class SimpleHttpServer {

  public void makeServer(String filepath, String filename) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(7771), 0);
    server.createContext("/filename", new InfoHandler(filename));
    server.createContext("/getFile", new GetHandler(filepath));
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class InfoHandler implements HttpHandler {
    
    String filename;
    
    InfoHandler(String name) {
      filename = name;
    }

    public void handle(HttpExchange t) throws IOException {
      String response = new String(filename);
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }

  static class GetHandler implements HttpHandler {
    
    String filepath;
    
    GetHandler(String path) {
      filepath = path;
    }
    public void handle(HttpExchange t) throws IOException {

      // add the required response header for a PDF file
      Headers h = t.getResponseHeaders();
      h.add("Content-Type", "File");

      // a PDF (you provide your own!)
      File file = new File (filepath);
      byte [] bytearray  = new byte [(int)file.length()];
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(bytearray, 0, bytearray.length);

      // ok, we are ready to send the response.
      t.sendResponseHeaders(200, file.length());
      OutputStream os = t.getResponseBody();
      os.write(bytearray,0,bytearray.length);
      os.close();
    }
  }
}