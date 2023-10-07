package com.controller

import com.MainApp
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent

@sfxml
class GuidePageController() {
  def handleBackToHomePage(action : ActionEvent) = {
    MainApp.showMenuPage()
  } 
}