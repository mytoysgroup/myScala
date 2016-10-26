package ibe.examples

import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable
import scala.util.control.Breaks

/**
  * Created by ingo on 26.09.2016.
  */
class FunctionTest extends FunSpec with Matchers {

  describe("In Scala") {
    it("a tuple of values can be returned") {
      def returnPair() = {
        val x = 42
        val y = "The answer to all questions"
        (x, y)
      }

      val (myInt: Int, myString: String) = returnPair
      myInt should not be (43)
      myInt should be(42)
      myString should be("The answer to all questions")
    }
  }

  it("a loop is breakable... in a slightly strange way") {
    class Thingy(myval: Int = 0) {
      def getValue = myval

      override def toString(): String = {
        "Thingy: " + myval
      }
    }

    val indexes = List(1, 2, 3, 4, 5)
    var result = mutable.ListBuffer[Thingy]()

    val loop = new Breaks;
    loop.breakable {
      for (a <- indexes) {
        println("Value of a: " + a)
        result += new Thingy(a)
        if (a == 3) {
          loop.break
        }
      }
    }
    val resultList = result.toList
    println("resultList: " + resultList)
    resultList.last.getValue should be(3)
  }

  it("for-loops are fun") {
    var x = 0
    for (a <- 1 to 3) {
      x += a
    }
    x should be(6)
  }

  it("for loops can have more than one iteration") {
    val results = mutable.Stack(2, 3, 3, 4)
    var sum = 0
    for (a <- 1 until 3; b <- 1 to 2) {
      println(s"a: $a and b: $b")
      (a + b) should be(results.pop())
      sum += (a + b)
    }
    sum should be(12)
  }

  it("for loops can can have a collection as source") {
    val myList = List("hund", "katze", "maus")

    for (txt <- myList) {
      println(txt)
      txt should contain oneOf("hund", "katze", "maus")
    }
  }

  it("for loops can be limited by filters") {
    val a : Int = 0
    val results = mutable.Stack(6, 12)
    val myList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    for (a <- myList
         if a % 2 == 0;
         if a % 3 == 0) {
           println(a)
           a should be (results.pop())
    }
  }

  it("for loops can be used like filter- and map statements") {
    val a : Int = 0
    val expected = mutable.Stack(12, 24)
    val myList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val results =
      for (a <- myList
         if a % 2 == 0;
         if a % 3 == 0)yield a*2
    results should be (expected)
  }
}