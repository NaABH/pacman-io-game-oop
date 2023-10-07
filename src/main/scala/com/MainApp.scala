/**
  This program is referring to practical address app framework
*/

package com
import com.model.Player
import com.controller.GameOverPageController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.stage.{Stage, Modality, StageStyle}
import scalafx.scene.Scene
import scalafx.scene.layout.AnchorPane
import scalafx.scene.image.Image
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import scalafx.collections.ObservableBuffer
import scalafx.Includes._
import javafx.{scene => jfxs} //shortcut for javafx.scene

object MainApp extends JFXApp {
  
  // Store data of current player
  var currentPlayer: Player = null

  // create observablebuffer of player history that contain player name and their highest score record
  val playerScoreBoardData = new ObservableBuffer[Player]()
  playerScoreBoardData += new Player("Wilson", 120) += 
  new Player("Admin", 999) += 
  new Player("Haley", 888)

  // load the rootlayout
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  // initialize scene
  val mainScene = new Scene {
    root = roots
  }
  
  // initialize stage
  stage = new PrimaryStage {
    title = "Pac-Man.io"
    icons += new Image("file:resources/icon.png")
    scene = mainScene
    centerOnScreen
    resizable = false
  }

  // show menu page
  def showMenuPage() = {
    val resource = getClass.getResource("view/MenuPage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

  // show player score  history page
  def showScoreHistoryPage() = {
    val resource = getClass.getResource("view/ScoreHistoryPage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

  // show player name page (for player to fill in their name before the game start)
  def showPlayerNamePage() = {
    val resource = getClass.getResource("view/PlayerNamePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 
 
  // show guideline page
  def showGuidePage() = {
    val resource = getClass.getResource("view/GuidePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

  // show game page
  def showGamePage() = {
    val resource = getClass.getResource("view/GamePage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

  // show gameover dialog page
  def showGameOverDialog() = {
    val resource = getClass.getResource("view/GameOverPage.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots2  = loader.getRoot[jfxs.Parent]
    val control = loader.getController[GameOverPageController#Controller]

    // create a gameover dialog to show when gameover
    val gameOverDialog = new Stage() {
      initModality(Modality.APPLICATION_MODAL)
      initOwner(stage)
      title = "Result"
      icons += new Image("file:resources/images/icon.png")
      initStyle(StageStyle.UNDECORATED)
      scene = new Scene {
        root = roots2
        stylesheets += getClass.getResource("css/gameOverPage.css").toString
      }
    }
    control.dialogStage = gameOverDialog
    gameOverDialog.show()
  } 

  // call to display the menu page when app start
  showMenuPage()

}
