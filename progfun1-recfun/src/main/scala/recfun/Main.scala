package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c==0) 1
      else if (r==c) 1
      else
      pascal(c-1,r-1)+pascal(c,r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def balanceInm(abiertos: Int, chars: List[Char]): Boolean = {
        if (chars.isEmpty) {
          abiertos==0
        }
        else if (abiertos<0) {
          false
        }
        else if (chars.head.equals('(')){
          balanceInm(abiertos+1,chars.tail)
        }
        else if (chars.head.equals(')')) {
          balanceInm(abiertos-1,chars.tail)
        }
        else {
          balanceInm(abiertos, chars.tail)
        }
      }
      balanceInm(0,chars)
      
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def countChangeInm(money: Int, acc:Int, coins: List[Int]): Int = {
        if (acc>money) 0
        else if (coins.isEmpty) {
          if (money==acc) 1
          else 0
        }
        else {
         // countChangeInm(money,acc+coins.head,coins.tail) +
          countChangeInm(money,acc+coins.head,coins) +
          countChangeInm(money,acc,coins.tail)
        }
        
      }
      countChangeInm(money,0,coins)
    }
  }
