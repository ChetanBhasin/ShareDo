package home

import netpro._

import scala.swing._
import scala.swing.event._

object Entry extends SimpleSwingApplication {

    lazy val lable = new Label("No operation")
    lazy val headingTxt = new Label("Select a file to share with others or recieve a file by clicking the second button")
    lazy val gridstack = new GridBagPanel {
        grid =>

        import scala.swing.GridBagPanel._

        val c = new Constraints
        c.fill = Fill.Horizontal
        val chooser = new FileChooser

        c.grid = (2, 2)
        val sendButton = new Button("Send File")
        sendButton.reactions += {
            case ButtonClicked(_) =>
                println("The send button is clicked now")
                chooser.showOpenDialog(grid)
                val server = new netpro.SimpleHttpServer
                server.makeServer(chooser.selectedFile.getAbsolutePath, chooser.selectedFile.getName)
                sendButton.visible = false
                lable.text = "Now: Sending file " + chooser.selectedFile.getName
        }
        layout(sendButton) = c

        c.grid = (3, 2)
        val getButton = new Button("Recieve File")
        getButton.reactions += {
            case ButtonClicked(_) =>
                println("The get button was clicked here")
                lable.text = "Now: Downloading a file"
                Grabber.getFile(lable)
        }
        layout(getButton) = c
    }

    lazy val ui: Panel = new BorderPanel {
        layout(headingTxt) = BorderPanel.Position.North
        layout(gridstack) = BorderPanel.Position.Center
        layout(lable) = BorderPanel.Position.South
    }

    lazy val top = new MainFrame {
        title = "ShareDO"
        contents = ui
    }

}
