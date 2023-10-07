package com.model

import scalafx.scene.image.Image
import scalafx.Includes._
import javafx.scene.paint.ImagePattern

class PacMan() extends GameObject {
    
    var XMoveSpeed = 3.0
    var YMoveSpeed = 3.0
    private val pacmanImage = new ImagePattern(new Image("file:resources/pacman.gif"))

    radius = 18
    // fill the pacman circle with image
    fill = pacmanImage

    //override the methods from the abstract class
    override def startPosition(): Unit = {
        layoutX = 385 
        layoutY = 460
    }

    def moveUp() : Unit = {
        if (layoutY.value < 0) {
            layoutY = 0
        }
        else {
            layoutY = layoutY.value - YMoveSpeed
        }
    }

    def moveDown() : Unit = {
        if (layoutY.value > 600 - 20) {
            layoutY = 600-20
        }
        else {
            layoutY = layoutY.value + YMoveSpeed
        }
    }

    def moveLeft() : Unit = {
        if (layoutX.value < 0) {
            layoutX = 0
        }
        else {
            layoutX = layoutX.value - XMoveSpeed
        }
    }

    def moveRight() : Unit = {
        if (layoutX.value > 800) {
            layoutX = 800
        }
        else {
            layoutX = layoutX.value + XMoveSpeed
        }
    }
}