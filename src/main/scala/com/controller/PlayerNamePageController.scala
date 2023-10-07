package com.controller
import com.MainApp
import com.model.{Player, GameData}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.control.{TextField, Alert}
import scalafx.event.ActionEvent

@sfxml
class PlayerNamePageController(
    private val playerNameField: TextField,
    private val upKeyImage: ImageView,
    private val downKeyImage: ImageView,
    private val leftKeyImage: ImageView,
    private val rightKeyImage: ImageView,
    private val mouseImage: ImageView,
    private val spaceKeyImage: ImageView,
    private val ctrlKeyImage: ImageView

    ) {
    private val dialogStage : Stage  = null
    upKeyImage.image = new Image("file:resources/ARROWUP.png")
    downKeyImage.image = new Image("file:resources/ARROWDOWN.png")
    leftKeyImage.image = new Image("file:resources/ARROWLEFT.png")
    rightKeyImage.image = new Image("file:resources/ARROWRIGHT.png")
    mouseImage.image = new Image("file:resources/mouse.png")
    spaceKeyImage.image = new Image("file:resources/SPACEALTERNATIVE.png")
    ctrlKeyImage.image = new Image("file:resources/CTRL.png")

    def handleBackToHomePage(action : ActionEvent) = {
      MainApp.showMenuPage()
    } 

    def handleStartGame(action : ActionEvent) = {
      if (isInputValid()){
        var person = getExistingPlayer(playerNameField.text.value)
        if (person == null) {
          MainApp.playerScoreBoardData += new Player(playerNameField.text.value, 0)
          MainApp.currentPlayer = MainApp.playerScoreBoardData(MainApp.playerScoreBoardData.length - 1)
        }
        else {
          MainApp.currentPlayer = person
        }
        GameData.resetAll
        MainApp.showGamePage()
      }
      
    } 

    //check input if it is null or empty
    def nullChecking (x : String): Boolean = {
      if (x == null || x.length == 0 || x.trim.length == 0){
        return true
      }
      else{
        return false
      }
    }

    //check input validation
    def isInputValid() : Boolean = {
      var errorMessage = ""

      if (nullChecking(playerNameField.text.value)) {
        errorMessage += "Player name cannot be empty !\n"
      }
      if (playerNameField.text.value.length > 20) {
        errorMessage += "Player name cannot be more than 20 characters !\n"
      }
      
      if (errorMessage.length() == 0) {
          return true;
      } else {
          // Show the error message.
          val alert = new Alert(Alert.AlertType.Error){
            initOwner(dialogStage)
            title = "Invalid Input Error"
            headerText = "Please provide a valid input."
            contentText = errorMessage
          }.showAndWait()

          return false;
      }
    }

    //get the player record from playerScoreBoardData
    def getExistingPlayer(name : String): Player = {
        val person = MainApp.playerScoreBoardData.find(a => {a.playerName.value == name})
        person match {
          case Some(person) => return person
          case None => return null
        }
    }
}