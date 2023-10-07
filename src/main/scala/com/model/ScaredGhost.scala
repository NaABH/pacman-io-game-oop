package com.model

import scala.util.Random
import scalafx.scene.image.Image
import scalafx.Includes._
import javafx.scene.paint.ImagePattern

class ScaredGhost() extends GameObject with RandomMove {
    
    var XMoveSpeed = 2
    var YMoveSpeed = 2
    val mark = 50

    radius = 15
    fill = new ImagePattern(new Image("file:resources/scaredGhost.gif"))

    //override the startPosition function from the abstract class
    override def startPosition(): Unit = {
        layoutX = Random.nextInt(800)
        layoutY = 0
    }

    //implement method from trait
    //move in X and Y direction with constant speed
    def move(): Unit = {     
        if (layoutX.value+radius.value < 0 || layoutX.value > 800) {
            XMoveSpeed = XMoveSpeed * -1
        }
        if (layoutY.value+radius.value < 0 || layoutY.value > 600) {
            YMoveSpeed = YMoveSpeed * -1
        }
        layoutY = layoutY.value + YMoveSpeed
        layoutX = layoutX.value + XMoveSpeed
    }

}
