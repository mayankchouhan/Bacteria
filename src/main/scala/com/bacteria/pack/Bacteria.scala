package com.bacteria.pack

class Bacteria(xKey:Int, yKey:Int, initialState:Int) {
  val x = xKey
  val y = yKey
  var state = initialState
  var liveNeighbourCount = 0

  /**
    * Method to change state of bacteria from live to dead and vice-versa.
    */
  def changeState: Unit = {
    state match {
      case 0 => state = 1
      case 1 => state = 0
    }
  }

  /**
    * Method to increment the liveNeighbourCount
    */
  def addNeighbour: Unit ={
    liveNeighbourCount += 1
  }
}