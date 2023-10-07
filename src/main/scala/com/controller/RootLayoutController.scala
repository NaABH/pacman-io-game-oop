package com.controller
import com.MainApp
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.scene.control.Alert
import scalafx.event.ActionEvent

@sfxml
class RootLayoutController() {

    def handleExit(action : ActionEvent) = {
        MainApp.stage.close()
    }

    def handleStartNewGame(action : ActionEvent) = {
        MainApp.showPlayerNamePage()
    }

    private val dialogStage: Stage = null
    
    def handleAbout(action : ActionEvent) = {
        val alert = new Alert(Alert.AlertType.Information){
          initOwner(dialogStage)
          title = "Pac-Man.io"
          headerText = "Version 1.0.0\nBuild: 1\nAuthor: Na Hang Wei"
          contentText = "Thank you for supporting."
        }.showAndWait()
    }
}