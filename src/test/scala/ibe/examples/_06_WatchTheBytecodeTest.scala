package ibe.examples

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by ingo on 26.09.2016.
  */
class _06_WatchTheBytecodeTest extends FunSpec with Matchers {
/***
  * IMPORTANT:
  * Im Zweifelsfall kann man z.B. mit "javap <path to class-file> nachschauen, wie Scala eine Klasse zu Bytecode
  * compiliert, z.B.
  * javap target/scala-2.11/test-classes/ibe/examples/_11a_InheritanceTest\$\$anonfun\$1\$Point\$1.class
  *
  * Das Ergebnis ist z.B.
  *
  * public final ibe.examples._11a_InheritanceTest$$anonfun$1 $outer;
  * public boolean isNotEqualInAStrangeWay(java.lang.Object);
  * public int init_x();
  * public int init_y();
  * public int x();
  * public void x_$eq(int);
  * public int y();
  * public void y_$eq(int);
  * public int getX();
  * public int getY();
  * public void move(int, int);
  * public java.lang.String toString();
  * public boolean isEqualInAStrangeWay(java.lang.Object);
  * public ibe.examples._11a_InheritanceTest$$anonfun$1 ibe$examples$_11a_InheritanceTest$$anonfun$Point$$$outer();
  * public ibe.examples._11a_InheritanceTest$$anonfun$1 ibe$examples$_11a_InheritanceTest$$anonfun$StrangeEqual$$$outer();
  * public ibe.examples._11a_InheritanceTest$$anonfun$1$Point$1(ibe.examples._11a_InheritanceTest$$anonfun$1, int, int);
  * }
  *
  * Another option is to use
  *    scalac -print <path to scala file> or
  *    scalac -Xprint:parse <path to scala file>
  *    scalac -Xprint:all <path to scala file>
  *
  *    z.B.
  *    scalac -print src/main/scala/ibe/examples/actor/SimpleActor.scala
  *
  *
  *    See also: http://alvinalexander.com/scala/how-to-disassemble-decompile-scala-source-code-javap-scalac-jad
  */


}
