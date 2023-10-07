package com.model

import scalafx.beans.property.ObjectProperty

//an object that is used to store the gamedata including score, pelletEaten and scaredGhostEaten
object GameData {
    var score = ObjectProperty[Int](0)
    var pelletEaten = ObjectProperty[Int](0)
    var scaredGhostEaten = ObjectProperty[Int](0)

    def resetScore: Unit = {
        score.value = 0
    }

    def resetPelletEaten: Unit = {
        pelletEaten.value = 0
    }

    def resetScaredGhostEaten: Unit = {
        scaredGhostEaten.value = 0
    }

    def resetAll(): Unit = {
        resetScore
        resetPelletEaten
        resetScaredGhostEaten
    }

}