package ibe

package object packageobject {
  val CONSTANT = 42
  def echo(a: Any): String = {a.toString}

  //enum
  object Margin extends Enumeration{
    type Margin = Value
    val TOP, BOTTOM, LEFT, RIGHT = Value
  }
}
