package com.controller
import com.MainApp
import com.model.{Game, PacMan, Pellet, Ghost, ScaredGhost, GameData}
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.control.Label
import scalafx.scene.shape.{Shape, Circle}
import scalafx.scene.{Scene, Group}
import scalafx.scene.input.{MouseEvent, KeyEvent, KeyCode}
import scalafx.animation._
import scalafx.scene.layout.AnchorPane
import scalafx.scene.paint.{Color, Paint}
import java.time.LocalDate

@sfxml
class GamePageController(
    private val gameScene: AnchorPane,
    private val scoreLabel: Label,
    private val pausedLabel: Label
    
    ) {

    private val game = new Game()
    private val player = game.player
    private val enemies = game.enemies
    private val foods = game.foods

    //initialise the game with setting up the environment
    private def initialise() = {
        gameScene.children += player
        player.startPosition
        gameScene.children += game.scaredGhost
    }

    //display score with the scoreLabel
    def showScore() = {
        scoreLabel.text = GameData.score.value.toString
    } 

    //define task1 for the game which is to add new food to the game (should be run every 2 seconds)
    def task1_addNewFood() = {
        if(game.foods.length < game.maxFoodNum){
            game.addFood
            var newFood = game.foods(foods.length-1)
            newFood.startPosition
            gameScene.children += newFood
        } 
    }

    //define task2 for the game which is to add new enemy to the game and add default survival marks (should be run every 5 seconds)
    def task2_addNewEnemy() = {
        GameData.score.value += 5 //survival marks
        if(game.enemies.length < game.maxEnemyNum){
            game.addEnemy
            var newEnemy = game.enemies(enemies.length-1)
            newEnemy.startPosition
            gameScene.children += newEnemy
        }
    }

    //define task3 for the game which is to add new ScaredGhost to the game (should be run every 15 seconds)
    def task3_addNewScaredGhost() = {
        if (game.scaredGhost == null) {
            game.addScaredGhost
            var newScaredGhost = game.scaredGhost
            newScaredGhost.startPosition
            gameScene.children += newScaredGhost
        }
    }

    def handleMouseMoved(cursor: MouseEvent) = {
        //make sure player move within the anchorpane
        if (player.layoutX.value >= 0 && player.layoutX.value <= gameScene.width.value) {
            player.layoutX = cursor.x - 0.0*player.radius.value
        }
        else {
            if (player.layoutX.value < 0) {
                player.layoutX = 0
            }
            else {
                player.layoutX = gameScene.width.value
            }
        }
        if (player.layoutY.value >= 0 && player.layoutY.value <= gameScene.height.value){
            player.layoutY = cursor.y - 0.0*player.radius.value
        }
        else {
            if (player.layoutY.value < 0) {
                player.layoutY = 0
            }
            else {
                player.layoutY = gameScene.height.value
            }
        }   
    }

    // the concept of handling multiple keys at same time is refering from https://www.youtube.com/watch?v=EcCMXoxqlLA
    private var upIsPressed = false
    private var downIsPressed = false
    private var leftIsPressed = false
    private var rightIsPressed = false

    MainApp.mainScene.onKeyPressed = (key: KeyEvent) => {
        key.code match {
          case KeyCode.Up => upIsPressed = true
          case KeyCode.Down => downIsPressed = true
          case KeyCode.Left => leftIsPressed = true
          case KeyCode.Right => rightIsPressed = true
          case KeyCode.Space => game.state = "Pause"; pausedLabel.visible = true
          case KeyCode.Control => game.state = "Start"; pausedLabel.visible = false
          case _=> 
        }
    }

    MainApp.mainScene.onKeyReleased = (key: KeyEvent) => {
        key.code match {
          case KeyCode.Up => upIsPressed = false
          case KeyCode.Down => downIsPressed = false
          case KeyCode.Left => leftIsPressed = false
          case KeyCode.Right => rightIsPressed = false
          case _=> 
        }
    }

    var starttime1 = 0L
    var starttime2 = 0L
    var starttime3 = 0L
    initialise
    val timer: AnimationTimer = AnimationTimer (currentTime => {
        if (game.state == "Start"){
            showScore
            enemies.foreach(e => e.move) //make all enemies(ghosts) move
            if (!(game.scaredGhost == null)) {
                game.scaredGhost.move
                if(game.checkCollisionScaredGhost(game.scaredGhost)) {gameScene.children -= game.scaredGhost; game.removeScaredGhost}
            }
            if(upIsPressed){player.moveUp; player.rotate = 270}
            if(downIsPressed){player.moveDown; player.rotate = 90}
            if(leftIsPressed){player.moveLeft; player.rotate = 180}
            if(rightIsPressed){player.moveRight ; player.rotate = 0} 
            if(game.checkCollisionGhost(enemies)) {game.state = "End"}
            if(game.checkCollisionPellet(foods)) {val crashedFood = game.getCollidedFood; gameScene.children -= crashedFood; game.removeFood(crashedFood)}

            var passedtime1 = currentTime - starttime1
            if (passedtime1 > 2e9){ //approximate 2 seconds
                starttime1 = currentTime
                task1_addNewFood
            }

            var passedtime2 = currentTime - starttime2
            if (passedtime2 > 5e9){ //approximate 5 seconds
                starttime2 = currentTime
                task2_addNewEnemy
            }

            var passedtime3 = currentTime - starttime3
            if (passedtime3 > 15e9){ //approximate 15 seconds
                starttime3 = currentTime
                task3_addNewScaredGhost
            }
        }

        else if(game.state == "End") {
            timer.stop
            //update the player data if the score exceed his/her previous highest score
            if (MainApp.currentPlayer.playerScore.value < GameData.score.value) {
                MainApp.currentPlayer.playerScore.value = GameData.score.value
                MainApp.currentPlayer.dateAchieve.value = java.time.LocalDate.now //fill in today's date
            }
            MainApp.showGameOverDialog
        }
    })

    //pause or start the game according to the keyaction
    if (game.state == "Pause") {
        timer.stop
    }
    else if (game.state == "Start") {
        timer.start
    }
}