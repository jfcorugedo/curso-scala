package org.hablapps
package fpinscala.scalaz


object IOProgramsState{
  
  // Ahora ya podemos implementar los programas de IO mediante for-comprehensions
  // y reutilizar todos los métodos genéricos que podemos definir para cualquier
  // tipo monádico (ifM, repeatUntil, etc.)
  import fpinscala.lenguajes.IO, IO.Syntax._

  // IOTrans[T] = IOState => (IOState, T) = State[IOState,T]
  import fpinscala.lenguajes.test.{IOS => IOState}, 
    IOState.{IOState => IOTrans},
    fpinscala.lenguajes.test.{PureIO => IOTrans}
  
  // Estado inicial

  val s1: IOState = IOState(reads=List("hi!", "bye"), writes=List())

  // Leemos algo

  val (s2, "hi!") = IOTrans.read(s1)
  val IOState(List("bye"),List()) = s2

  val IOState(List("bye"),List()) = IOTrans.read.exec(s1)

  val msg: String = IOTrans.read.eval(s1)

}