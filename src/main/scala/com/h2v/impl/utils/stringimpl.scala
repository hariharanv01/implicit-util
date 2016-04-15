package com.h2v.impl.utils

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{ Set => MSet }
import scala.util.Random

object stringimpl {

  val random = Random

  /**
   * Shuffles the String
   */
  def shuffle(s: String): String = {
    val lb: ListBuffer[Char] = ListBuffer()
    for (i <- 1 until s.length) {
      val temp = lb(i)
      val r = random.nextInt(i)
      lb(i) = lb(r)
      lb(r) = temp
    }
    lb mkString ""
  }

  implicit class StringImpl(s: String) {

    /**
     * Union-All of the current String with the given String. Duplicate elements are not removed
     */
    def |(o: String): String = s union o

    /**
     * Union of the current String with the given String. Duplicate elements are removed
     */
    def U(o: String): String = (s union o) distinct

    /**
     * Intersection of the current String with the given String
     */
    def &(o: String): String = s intersect o

    /**
     * current String minus the given String
     */
    def -(o: String): String = s filterNot (o contains _)

    /**
     * Move the String by 'n' elements to the Right
     */
    def |>>(n: Int): String = s dropRight n

    /**
     * Move the String by 'n' elements to the Left
     */
    def <<|(i: Int): String = s drop i

    /**
     * Unary reverse String operator.
     * i.e. ~List(1,2,3) = List(3,2,1)
     */
    def unary_~ = s reverse

    /**
     * Randomly pick a Char from the String
     */
    def rand(): Char = s(random.nextInt(s length))

    /**
     * Rotate the Sting right by 'n' elements
     */
    def >>(n: Int): String = (s.dropRight(n).reverse + s.takeRight(n).reverse).reverse

    import scala.collection.mutable.{ Set => MSet }
    /**
     * Return a sample of 'n' elements from the String
     */
    def sample(n: Int): String = {
      val lb: ListBuffer[Char] = ListBuffer()
      val set = MSet[Int]()
      var i = n
      while (i > 0) {
        val r = random nextInt s.length
        if (!(set contains r)) {
          lb += s(r)
          i -= 1
          set += r
        }
      }
      lb mkString ""
    }

  }

}