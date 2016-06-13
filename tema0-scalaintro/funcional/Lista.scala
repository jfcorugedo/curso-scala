package org.hablapps.scalaintro.funcional

// 1. Crear estructura propia para poder trabajar con listas.
//Es un tipo de datos algebráico (ADT)
//sealed trait Lista
//class Cons(val elem: Int,val resto: Lista) extends Lista
//class Fin() extends Lista

// 2. Añadimos un método para insertar un nuevo elemento en la lista (por la
// cabeza)
//sealed significa que no se puede extender fuera de este fichero
//sealed trait Lista {
//	def insertar(i: Int): Lista = new Cons(i, this)
//}

// 3. Convertimos nuestras clases en "case" y añadimos el método `suma`, que
// suma todos los elementos de la lista, o devuelve 0 en caso de que la lista
// sea vacía.
//Cuando se declara una clase como case, se añade azucar sintactico: Ya no es necesario val en el constructor, todo los argumentos seran atributos de clase
//sealed trait Lista {
//	def insertar(i: Int): Lista = new Cons(i, this)
//	//En vez de implementar este metodo en cada subclase (Cons y Fin) se hace aqui
//	def suma: Int = this match {
//		case Cons(h, t) => h + t.suma//Se esta usando un extractor para sacar los valores de dentro de cons
//		case Fin() => 0
//	}
//}
case class Cons(elem: Int,resto: Lista) extends Lista
case class Fin() extends Lista

//Para descoplar datos e implementacion podemo hacer
object Foo {
	def suma(l: Lista): Int = l match {
		case Cons(h, t) => h + suma(l)//Se esta usando un extractor para sacar los valores de dentro de cons
		case Fin() => 0
	}
}

// 4. Añadimos el método `map` que recibe una lambda (Int => Int) para mapear
// todos los elementos de esta lista.
sealed trait Lista {
	def insertar(i: Int): Lista = new Cons(i, this)
	//En vez de implementar este metodo en cada subclase (Cons y Fin) se hace aqui
	def suma: Int = this match {
		case Cons(h, t) => h + t.suma//Se esta usando un extractor para sacar los valores de dentro de cons
		case Fin() => 0
	}
	def map(f: Int => Int): Lista = this match {

		case Cons(h, t) => new Cons(f(h), t.map(f))
		case fin: Fin => fin
	}
}

/* Otra implementacion
  def insertar(head: Int): Lista = new Cons(head, this)
  def suma: Int = this match {
    case Cons(head, tail) => head + tail.suma
    case Fin() => 0
  }
  def map(f: Int => Int): Lista = this match {
    case Cons(head, tail) => Cons(f(head), tail.map(f))
    case Fin() => Fin()
  }
}

case class Cons(head: Int, tail: Lista) extends Lista
case class Fin() extends Lista
* 
*/
