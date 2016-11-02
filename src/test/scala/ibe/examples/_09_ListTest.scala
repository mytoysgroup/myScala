package ibe.examples

import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable.ArrayBuffer

class _09_ListTest extends FunSpec with Matchers {

  describe("A list") {
    it("is immutable") {
      val nums = List(1, 2, 3, 4)
      List() should be(List.empty)
      List().isEmpty should be(true)
    }

    it("can append new elements") {
      var myList = List(1, 2, 3)
      myList = 44 +: myList :+ 55
      myList should be(List(44, 1, 2, 3, 55))

      //=> consider using ArrayBuffer for appending new elements
    }

    it("can be multidimensional") {
      val matrixLists: List[List[Int]] =
        List(
          List(1, 0, 0),
          List(0, 1, 0),
          List(0, 0, 1)
        )

      val matrixArrays: Array[Array[Int]] =
        Array(
          Array(1, 0, 0),
          Array(0, 1, 0),
          Array(0, 0, 1)
        )

      matrixLists should be(matrixArrays)
    }

    it("has some interesting options to operate with.") {
      Nil should be(List())
      val listNum = (1 :: (2 :: (3 :: Nil)))
      listNum should be(List(1, 2, 3))

      val listFruit = List("apple", "cherry", "pear")
      val combineList_as_regular_function_call = listNum.:::(listFruit)
      val combineList_as_suffix_notation = listNum ::: listFruit //which makes it pretty expressive :)

      combineList_as_regular_function_call should be(List("apple", "cherry", "pear", 1, 2, 3)) //don't know why it is
      // "reversed" this way
      combineList_as_suffix_notation should be(List(1, 2, 3, "apple", "cherry", "pear"))

      List.concat(listNum, listFruit) should be(List(1, 2, 3, "apple", "cherry", "pear"))

      listFruit.head should be("apple")
      listFruit.tail should be(List("cherry", "pear"))

      List.fill(3)("apples") should be(List("apples", "apples", "apples")) //fill list 3 times with "apples"
      List.tabulate(6)(n => n * n) should be(List(0, 1, 4, 9, 16, 25))
      List.tabulate(5)(_ + 2) should be(List(2, 3, 4, 5, 6))
      List.tabulate(2, 3)(_ + 3 * _) should be(List(List(0, 3, 6), List(1, 4, 7))) //first _+ seems to be only there for
      //validity-reasons 3*_ is calculated
      listFruit.reverse should be(List("pear", "cherry", "apple"))

      //List(1,2) + 3 isn't supported anymore because it takes much more time than prepending with :: and reversing

      listNum.addString(new StringBuilder(), "//").toString() should be("1//2//3")
      listNum.contains(2) should be(true)
      listNum.contains(4) should be(false)

      listNum.dropRight(1) should be(List(1, 2))
      listNum.drop(1) should be(List(2, 3))
    }
  }

  describe("Lists of Objects") {

    class Balloon(var color: String = "blue", var filled: Boolean = false) {
      def canEqual(other: Any): Boolean = other.isInstanceOf[Balloon]

      override def equals(other: Any): Boolean = other match {
        case that: Balloon =>
          (that canEqual this) &&
            color == that.color &&
            filled == that.filled
        case _ => false
      }

      override def hashCode(): Int = {
        val state = Seq(color, filled)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
      }

      override def toString = s"Balloon($color, $filled)"
    }

    it("can be processed in a variety of ways") {
      val balloons = (new ArrayBuffer[Balloon]
        :+ new Balloon("g", false)
        :+ new Balloon("r", true)
        :+ new Balloon("y", true)
        :+ new Balloon("r", true)
        :+ new Balloon("g", true)
        :+ new Balloon("r", false)).toList

      balloons.exists(
        (b: Balloon) => b.color == "y" && b.filled == true
      ) should be(true)

      balloons.exists(
        (b: Balloon) => b.color == "y" && b.filled == false
      ) should be(false)

      balloons.contains(new Balloon("y", true)) should be(true)
      balloons.contains(new Balloon("y", false)) should be(false)

      balloons.filterNot(               //red balloons are removed
        (b : Balloon) => b.color == "r"
      ).exists(                         // and thus shouldn't exist anymore
        (b: Balloon) => b.color =="r"
      ) should be (false)

      balloons.filterNot(               //red balloons are removed
        (b : Balloon) => b.color == "r"
      ).exists(                         // and thus yellow ones still should exist
        (b: Balloon) => b.color =="y"
      ) should be (true)


      def isRed(b: Balloon) : Boolean = (b.color=="r")
      val justRed = balloons.filter(isRed)
      println(justRed)
      justRed.forall(isRed) should be (true)

      //balloons.

      //TODO dropWhile takeWhile ...
    }
  }

  /**
    * Example by https://www.garysieling.com/blog/scala-collect-example
    */
  describe("Special functions like") {
    it("'collect' can help to transform lists in an easy way") {
      /** converts a limited amount of data types into Int. Ignores other data types **/
      val convertFn: PartialFunction[Any, Int] = {
        case i: Int => i;
        case s: String => s.toInt;
        case Some(s: String) => s.toInt
      }

      List(0, 1, "2", "3", Some(4), Some("5")).collect(convertFn) should be(List(0, 1, 2, 3, 5))
    }

    it("'aggregate' can help to calculate parallely with trees"){
      val listOfBinaries = List("001", "010", "11", "100")
      listOfBinaries.aggregate(0)( //constant to which later values are added
        { (sum, str) => sum + Integer.parseInt(str,2) }, //turn binary into Int and add it to sum
        { (p1, p2) => p1 + p2 } //add sums
      ) should be (10)
    }
    it("'union', 'intersection' and 'difference' help to do it the 'Venn' way"){
      List(1,3).union(List(2,3)) should be ((List(1,3,2,3)))
      List(1,2,2,3).intersect(List(2,2)) should be (List(2,2))
    }

  }

}