package com.model

import scalafx.scene.shape.Circle
import scala.util.Random

abstract class GameObject() extends Circle{

    var XMoveSpeed: Double
    var YMoveSpeed: Double

    def startPosition: Unit = {
        layoutX = Random.nextInt(800) 
        layoutY = Random.nextInt(600)
    }

}    