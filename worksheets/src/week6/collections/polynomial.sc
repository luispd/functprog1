package week6.collections

object polynomial {
  class Poly(val terms: Map[Int, Double]) {
    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }
    override def toString =
      (for ((exp,coeff)<-terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString "+"
  }
  
  val p1 = new Poly(Map(1->2.0, 3 -> 4.0, 5 -> 6.2))
                                                  //> p1  : week6.collections.polynomial.Poly = 6.2x^5+4.0x^3+2.0x^1
  p1.terms(3)                                     //> res0: Double = 4.0
  val p2 = new Poly(Map(0->3.0, 3 -> 7.0))        //> p2  : week6.collections.polynomial.Poly = 7.0x^3+3.0x^0
  p1 + p2                                         //> res1: week6.collections.polynomial.Poly = 6.2x^5+11.0x^3+2.0x^1+3.0x^0
  
  // Mejor
  class ImprovedPoly(val terms0: Map[Int, Double]) {
    def this(bindings: (Int,Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0
    def + (other: ImprovedPoly) = new ImprovedPoly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
    override def toString =
      (for ((exp,coeff)<-terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString "+"
  }
  
  val iP1 = new ImprovedPoly(1->2.0, 3 -> 4.0, 5 -> 6.2)
                                                  //> iP1  : week6.collections.polynomial.ImprovedPoly = 6.2x^5+4.0x^3+2.0x^1
  iP1.terms(3)                                    //> res2: Double = 4.0
  iP1.terms(7)                                    //> res3: Double = 0.0
                                                 
  val iP2 = new ImprovedPoly(Map(0->3.0, 3 -> 7.0))
                                                  //> iP2  : week6.collections.polynomial.ImprovedPoly = 7.0x^3+3.0x^0
                                                  
  iP1 + iP2                                       //> res4: week6.collections.polynomial.ImprovedPoly = 6.2x^5+11.0x^3+2.0x^1+3.0
                                                  //| x^0

  // Mejor aun (mas eficiente)
  /* the one with foldLeft is more efficient because what happens here is that each of these bindings will be immediately added to our terms Maps so,
   we build up the result directly, whereas before, we would create another list of terms that contain the adjusted terms and then we would concatenate this list to the original one.
  So the version with foldLeft avoids this creation of the intermediate list data structure, so in that sense. It looks like it's more efficient*/
  class FoldLeftPoly(val terms0: Map[Int, Double]) {
    def this(bindings: (Int,Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0
    def + (other: FoldLeftPoly) = new FoldLeftPoly((other.terms foldLeft terms)(addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }
    override def toString =
      (for ((exp,coeff)<-terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString "+"
  }
  
  val fLP1 = new FoldLeftPoly(1->2.0, 3 -> 4.0, 5 -> 6.2)
                                                  //> fLP1  : week6.collections.polynomial.FoldLeftPoly = 6.2x^5+4.0x^3+2.0x^1
  val fLP2 = new FoldLeftPoly(Map(0->3.0, 3 -> 7.0))
                                                  //> fLP2  : week6.collections.polynomial.FoldLeftPoly = 7.0x^3+3.0x^0
  
  fLP1 + fLP2                                     //> res5: week6.collections.polynomial.FoldLeftPoly = 6.2x^5+11.0x^3+2.0x^1+3.0
                                                  //| x^0
  
}