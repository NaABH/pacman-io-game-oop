package com.controller

import com.MainApp
import com.model.GameData
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.scene.control.Label
import scalafx.event.ActionEvent

@sfxml
class GameOverPageController(
    private val scoreLabel: Label,
    private val highestScoreLabel: Label,
    private val pelletLabel: Label,
    private val scaredGhostLabel: Label

) {
    var dialogStage: Stage  = null

    def showData(): Unit = {
        scoreLabel.text = GameData.score.value.toString
        highestScoreLabel.text = MainApp.currentPlayer.playerScore.value.toString
        pelletLabel.text = GameData.pelletEaten.value.toString
        scaredGhostLabel.text = GameData.scaredGhostEaten.value.toString
        GameData.resetAll
    }
    showData

    def handleBackToHomePage(action : ActionEvent) = {
        dialogStage.close()
        MainApp.showMenuPage()
    } 

    def handleStartNewGame(action : ActionEvent) = {
        dialogStage.close()
        MainApp.showPlayerNamePage()
    }
    
    def handlePlayerRecord(action : ActionEvent) = {
        dialogStage.close()
        MainApp.showScoreHistoryPage()
     } 


}