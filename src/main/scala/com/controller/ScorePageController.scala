package com.controller

import com.MainApp
import com.model.Player
import scalafxml.core.macros.sfxml
import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.control.{TableView, TableColumn}
import scalafx.event.ActionEvent
import scalafx.Includes._

@sfxml
class ScorePageController(
    private val playerScoreTable : TableView[Player],
    private val playerNameColumn : TableColumn[Player, String],
    private val scoreColumn : TableColumn[Player, Int],
    private val dateColumn : TableColumn[Player, String],
    private val logoImage: ImageView
    ) {

    logoImage.image = new Image("file:resources/logo2.png")

    // initialize tableview display contents model
    playerScoreTable.items = MainApp.playerScoreBoardData

    // initialize columns's cell values
    playerNameColumn.cellValueFactory = {_.value.playerName}
    scoreColumn.cellValueFactory  = {_.value.playerScore} 
    dateColumn.cellValueFactory  = {_.value.dateAchieve.asString}

    //navigate to home page
    def handleBackToHomePage(action : ActionEvent) = {
      MainApp.showMenuPage()
    }

}





 


