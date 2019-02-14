package com.bacteria.pack

import org.scalatest.{FunSuite, Matchers}

import scala.collection.mutable.ListBuffer

class BacteriaTest extends FunSuite with Matchers {

  val b = BacteriaTrayMap

  val inputTest1 : ListBuffer[(Int, Int)] = ListBuffer((1,2), (2,2), (3,2),
                                    (1000000001,1000000002), (1000000002,1000000002), (1000000003,1000000002))
  val outputTest1 :ListBuffer[(Int, Int)] = ListBuffer((2,1), (2,2), (2,3),
                                    (1000000002,1000000001), (1000000002,1000000002), (1000000002,1000000003))
  val inputTest2 :ListBuffer[(Int, Int)] = ListBuffer((0,0), (0,3), (3,0), (3,3))

  val inputTest3 :ListBuffer[(Int, Int)] = ListBuffer((0,0), (1,0), (1,1), (1,2), (2,0))
  val outputTest3 :ListBuffer[(Int, Int)] = ListBuffer((0,0), (1,0), (2,0))

  val inputTest4 :ListBuffer[(Int, Int)] = ListBuffer((2,3), (3,2), (3,3))
  val outputTest4 :ListBuffer[(Int, Int)] = ListBuffer((2,2), (2,3), (3,2), (3,3))

  test("test1 - test sample input provided") {
    assert(outputTest1.sorted == b.processInputData(inputTest1).sorted)
  }
  test("test2 - All die due to under population") {
    assert(Nil == b.processInputData(inputTest2))
  }
  test("test3 - Overpopulation for (1,1)| 2 neighbours for (0,0) and (2.0)") {
    assert(outputTest3.sorted == b.processInputData(inputTest3).sorted)
  }
  test("test4 - Reproduction for (2,2) with 3 neighbours") {
    assert(outputTest4.sorted == b.processInputData(inputTest4).sorted)
  }
  test("test5 - Live bacteria with 3 neighbours survives. No change to 4 square neighbours") {
    assert(outputTest4.sorted == b.processInputData(outputTest4).sorted)
  }

}
