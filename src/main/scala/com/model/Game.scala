package com.model

import com.model.{PacMan, Pellet, Ghost, ScaredGhost}
import scalafx.scene.shape.Shape
import scala.collection.mutable.ListBuffer

class Game() {
    var state = "Start"
    val player = new PacMan()
    val enemies = ListBuffer[Ghost]() //ListBuffer is selected because it is always accessed linearly to check collision
    val maxEnemyNum = 8
    val foods = ListBuffer[Pellet]()
    val maxFoodNum = 20
    var scaredGhost: ScaredGhost = new ScaredGhost

    //the three checkCollision methods below cannot be implement as generic method because ListBuffer call is no variant
    //check if the ghosts are collided with the Pac-Man
    def checkCollisionGhost(list: ListBuffer[Ghost]): Boolean = {
        var noCollision = list.forall(a => {Shape.intersect(player, a).boundsInLocal.value.isEmpty})
        if(!noCollision) {
            return true
        }
        else {
            return false
        }
    }

    //check if the pellets are collided with the Pac-Man
    def checkCollisionPellet(list: ListBuffer[Pellet]): Boolean = {
        var noCollision = list.forall(a => {Shape.intersect(player, a).boundsInLocal.value.isEmpty})
        if(!noCollision) {
            return true
        }
        else {
            return false
        }
    }

    //check if the scared ghost is collided with the Pac-Man
    def checkCollisionScaredGhost(a: ScaredGhost): Boolean = {
        var noCollision = Shape.intersect(player, a).boundsInLocal.value.isEmpty
        if(!noCollision) {
            GameData.score.value += scaredGhost.mark
            GameData.scaredGhostEaten.value += 1
            return true
        }
        else {
            return false
        }
    }

    //get the food that collide with the Pac-Man
    def getCollidedFood(): Pellet = {
        val crashedFood = foods.find(a => {!(Shape.intersect(player, a).boundsInLocal.value.isEmpty)})
        crashedFood match {
            case Some(crashedFood) => GameData.score.value += crashedFood.mark; GameData.pelletEaten.value += 1; return crashedFood
            case None => return null
        }    
    }

    def addEnemy(): Unit = {
        if (enemies.length < maxEnemyNum){
            enemies += (new Ghost())
        }
    }

    def addFood(): Unit = {
        if (foods.length < maxFoodNum){
            foods += (new Pellet())
        }
        else {
            removeFood(foods.head)
            foods += (new Pellet())
        }
    }

    def removeFood(a: Pellet): Unit = {
        foods -= (a)
    }

    def removeScaredGhost(): Unit = {
        scaredGhost = null
    }

    def addScaredGhost(): Unit = {
        scaredGhost = new ScaredGhost
    }

}
