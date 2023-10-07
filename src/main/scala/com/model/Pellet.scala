package com.model

import scalafx.scene.paint.Color

class Pellet() extends GameObject {
    
   var XMoveSpeed = 0.0
   var YMoveSpeed = 0.0
   val mark: Int = 5
    
    radius = 5
    fill = Color.Yellow

    //inherit startPosition() methods from the GameObject but no overriding
}