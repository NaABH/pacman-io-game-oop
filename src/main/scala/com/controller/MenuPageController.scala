package com.controller

import com.MainApp
import scalafxml.core.macros.sfxml
import scalafx.scene.image.{ImageView, Image}
import scalafx.event.ActionEvent

@sfxml
class MenuPageController(
  private val logoImage: ImageView
) {
  logoImage.image = new Image("file:resources/logo.png")

  def handleStartGame(action : ActionEvent) = {
    MainApp.showPlayerNamePage()
  } 

  def handlePlayerRecord(action : ActionEvent) = {
    MainApp.showScoreHistoryPage()
  } 

  def handleHowToPlay(action : ActionEvent) = {
    MainApp.showGuidePage()
  } 

  def handleExit(action : ActionEvent) = {
    MainApp.stage.close()
  } 

}