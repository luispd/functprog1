package week6.collections

object combinaciones {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val s : List[Int] = List(7,3,9,5)               //> s  : List[Int] = List(7, 3, 9, 5)
  
  
  def combinacionesAux(s: List[Int], parcial: List[Int], n: Int): List[List[Int]] =  {
    if ((parcial.length == n) || (s.isEmpty)) {
      List(parcial)
    }
    else {
     combinacionesAux(s.tail,s.head::parcial,n):::combinacionesAux(s.tail,parcial,n)
    }
  }                                               //> combinacionesAux: (s: List[Int], parcial: List[Int], n: Int)List[List[Int]]
                                                  //| 
  
  
      combinacionesAux(s,Nil,s.length)            //> res0: List[List[Int]] = List(List(5, 9, 3, 7), List(9, 3, 7), List(5, 3, 7),
                                                  //|  List(3, 7), List(5, 9, 7), List(9, 7), List(5, 7), List(7), List(5, 9, 3), 
                                                  //| List(9, 3), List(5, 3), List(3), List(5, 9), List(9), List(5), List())
  
}