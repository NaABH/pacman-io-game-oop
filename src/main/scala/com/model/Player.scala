package com.model

import scalafx.beans.property.{StringProperty, ObjectProperty}
import java.time.LocalDate;

// store player information
class Player ( playerNameInput : String, playeScoreInput : Int){
  val playerName = new StringProperty(playerNameInput)
  var playerScore = ObjectProperty[Int](playeScoreInput)
  var dateAchieve = ObjectProperty[LocalDate](LocalDate.of(2022, 7, 1))
  
}
