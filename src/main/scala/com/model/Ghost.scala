package com.model

import scala.util.Random
import scalafx.scene.image.Image
import scalafx.Includes._
import javafx.scene.paint.ImagePattern

class Ghost() extends GameObject with RandomMove {
    
    var XMoveSpeed = 1.0
    var YMoveSpeed = 1.0
    private val redGhostImage = new ImagePattern(new Image("file:resources/redGhost.png"))
    private val orangeGhostImage = new ImagePattern(new Image("file:resources/orangeGhost.png"))
    private val pinkGhostImage = new ImagePattern(new Image("file:resources/pinkGhost.png"))
    private val ghostImages = List(redGhostImage, orangeGhostImage, pinkGhostImage)
    
    radius = 15
    // fill the ghost circle with random image
    fill = ghostImages(Random.nextInt(3))

    //override the methods from the abstract class
    override def startPosition(): Unit = {
        layoutX = Random.nextInt(800)
        layoutY = 0
    }

    //implement method from trait
    def move(): Unit = {     
        if (layoutX.value+radius.value < 0 || layoutX.value > 800) {
            XMoveSpeed = XMoveSpeed * -1.005
        }
        if (layoutY.value+radius.value < 0 || layoutY.value > 600) {
            YMoveSpeed = YMoveSpeed * -1.005
        }
        layoutY = layoutY.value + YMoveSpeed
        layoutX = layoutX.value + XMoveSpeed
    }

}
