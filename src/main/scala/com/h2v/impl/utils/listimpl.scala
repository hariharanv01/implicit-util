package com.h2v.impl.utils

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{ Set => MSet }
import scala.util.Random

object listimpl {

  val random = Random

  /**
   * Shuffles the given list
   */
  def shuffle[A](l: List[A]): List[A] = {
    val lb: ListBuffer[A] = l.to[ListBuffer]
    for (i <- 1 until lb.size) {
      val temp = lb(i)
      val r = random.nextInt(i)
      lb(i) = lb(r)
      lb(r) = temp
    }
    lb toList
  }

  implicit class ListUtil[A](l: List[A]) {

    /**
     * Union-All of the current List with the given List. Duplicate elements are not removed
     */
    def |(o: List[A]): List[A] = l union o

    /**
     * Union of the current List with the given List. Duplicate elements are removed
     */
    def U(o: List[A]): List[A] = (l union o) distinct

    /**
     * Intersection of the current List with the given List
     */
    def &(o: List[A]): List[A] = l intersect o

    /**
     * current List minus the given List
     */
    def -(o: List[A]): List[A] = l filterNot (o contains _)

    /**
     * Move the List by 'n' elements to the Right
     */
    def |>>(n: Int): List[A] = l dropRight n

    /**
     * Move the List by 'n' elements to the Left
     */
    def <<|(i: Int): List[A] = l drop i

    /**
     * Unary reverse list operator.
     * i.e. ~List(1,2,3) = List(3,2,1)
     */
    def unary_~ = l reverse

    /**
     * Randomly pick an element from the List
     */
    def rand: A = l(random.nextInt(l.size))

    /**
     * Rotate the List right by 'n' elements
     */
    def >>(n: Int): List[A] = (l.dropRight(n).reverse ::: l.takeRight(n).reverse).reverse

    /**
     * Monadic 'return' function
     */
    def pure(a: A): List[A] = List(a)

    /**
     * Monadic 'bind' function that can be used for computational pipeline
     */
    def >>=[B](fn: (A => List[B])): List[B] = l.flatMap(fn(_))

    import scala.collection.mutable.{ Set => MSet }
    /**
     * Return a sample of 'n' elements from the List
     */
    def sample(n: Int) = {
      val lb: ListBuffer[A] = ListBuffer()
      val set = MSet[Int]()
      var i = n
      while (i > 0) {
        val r = random nextInt l.size
        if (!(set contains r)) {
          lb += l(r)
          i -= 1
          set += r
        }
      }
      lb toList
    }

  }

}