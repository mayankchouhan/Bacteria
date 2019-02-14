package com.bacteria.pack

import scala.collection.mutable.ListBuffer

object BacteriaTrayMap {

    /**
      * bacteriaTray is implemented as a MAP with key -> values where key is the x and y coordinates on tray
      * and values is the Bacteria Object that contains state and liveNeighbourCount
      */
    var bacteriaTray: Map[String, Bacteria] = Map()


    // Make a string to use as a Map key
    def keyString(x:Int, y:Int):String = x + "," + y

    // For (x,y), return each positive neighbouring coordinate and self
    def neighbours(x:Int, y:Int):Set[(Int, Int)] = for (_x <- Set(math.abs(x - 1), x, x + 1); _y <- Set(math.abs(y - 1), y, y + 1))
      yield (_x, _y)

    /**
      * Main function.
      * @param args
      */
    def main(args:Array[String]):Unit = {

      // Read StdIn until an end line is reached
      var input = ""
      var inputList = new ListBuffer[(Int, Int)]()

      println("Please enter List of coordinates for Bacteria Petri Dish Tray")
      println("Please enter end when you are done")
      while (input != "end") {
        input = io.StdIn.readLine()
        if (input != "end") {
          val (x: Int, y: Int) = (input.split(",")(0).toInt, input.split(",")(1).toInt)
          inputList += ((x, y))
        }
      }
      inputList.foreach(println)
      println("Output after one generation")
      val finalOutput = processInputData(inputList: ListBuffer[(Int, Int)])
    }
  /**
    * Method to update the bacteriaTray Map with list input provided
    *
    * @param x key - Tray coordinate
    * @param y key - Tray coordinate
    * @param state
    * @return Bacteria Object
    */
  def bacteriaTrayAdd(x: Int, y: Int, state: Int): Bacteria = {
    val key = keyString(x, y)
    bacteriaTray.getOrElse(key, {
      bacteriaTray = bacteriaTray.updated(key, new Bacteria(x, y, state))
      bacteriaTray(key)
    })
  }


  def processInputData(inputBacteriaList: ListBuffer[(Int, Int)]) = {

    var outputList = new ListBuffer[(Int, Int)]()

    inputBacteriaList.map { coordinates => bacteriaTrayAdd(coordinates._1, coordinates._2, 1) }


    // Find neighbours and add to list
    bacteriaTray.values foreach { bacteria =>
      neighbours(bacteria.x, bacteria.y) foreach { neighbour =>
        bacteriaTrayAdd(neighbour._1, neighbour._2, 0)
      }
    }


    // Add a live neighbour count to each bacteria
    bacteriaTray.values foreach (bacteria => {
      bacteriaTray.values foreach (neighbour => {
        if (neighbour.state == 1 &&
          math.abs(bacteria.x - neighbour.x) <= 1 && math.abs(bacteria.y - neighbour.y) <= 1 &&
          !(bacteria.x == neighbour.x && bacteria.y == neighbour.y))
          bacteria.addNeighbour
      })
    })


    // Apply rules
    bacteriaTray.values foreach (bacteria => {
      if (bacteria.state == 1 && (bacteria.liveNeighbourCount < 2 || bacteria.liveNeighbourCount > 3))
        bacteria.changeState
      if (bacteria.state == 0 && bacteria.liveNeighbourCount == 3)
        bacteria.changeState
    })


    // Output
    bacteriaTray.values foreach { bacteria =>
      if (bacteria.state == 1) {
        outputList += ((bacteria.x, bacteria.y))
      }
    }

    //Display output list on console
    outputList foreach (println)
    println("end")

    // Reinitialize bacteriaTray Map
    bacteriaTray = Map()

    // Return outputList to Main
    outputList
  }
}
