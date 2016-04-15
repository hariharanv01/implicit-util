package com.h2v.impl.utils.integer

import scala.collection.mutable.ListBuffer
import scala.math.ceil
import scala.math.sqrt

package object _integer {

  /**
   * Overflow safe predecessor
   */
  def pred(i: Int): Option[Int] = if (i > Int.MinValue) Some(i - 1) else None

  /**
   * Overflow safe successor
   */
  def succ(i: Int): Option[Int] = if (i < Long.MaxValue) Some(i + 1) else None

  /**
   * Greatest common divisor
   */
  def gcd(i: Int, j: Int): Int = {
    require(i >= 0 && j >= 0, "Both the terms need to be non-negative")
    if (i % j == 0) j else gcd(j % i, i)
  }

  /**
   * Least common multiple
   */
  def lcm(i: Int, j: Int): Long = (i.toLong * j) / gcd(i, j)

  /**
   * Factors of the number
   */
  def factors(i: Int) = {
    require(i >= 0, "The term need to be non-negative")
    val lb = ListBuffer[Int]()
    for (j <- 1 to ceil(sqrt(i)).toInt if (i % j == 0)) {
      lb += j
    }
    lb += i
    lb toList
  }

  /**
   * power of the number
   */
  def pow(j: Int, k: Int): Int = {
    require(k >= 0, "The exponent term need to be non-negative")
    (j, k) match {
      case (1, _) | (_, 0) | (0, _) => 1
      case (j, 1)                   => j
      case (j, k) if (k % 2 == 0)   => pow(j * j, k / 2)
      case _                        => pow(j * j, (k + 1) / 2) / j
    }
  }

  implicit class IntUtil(i: Int) {

    /**
     * 2th power of the current number
     */
    def ^^ = 1 << i

    /**
     * power of the number
     */
    def ^(n: Int): Int = {
      pow(i, n)
    }

    /**
     * approx equal to
     */
    def ~=(o: Int, delta: Int = 0) = (o - i).abs <= delta

    /**
     * way greater than
     */
    def >>>>(o: Int, delta: Int = 1000000): Boolean = (i - o) >= delta

    /**
     * way less than
     */
    def <<<<(o: Int, delta: Int = 1000000) = (o - i) >= delta

    /**
     * Greatest common divisor
     */
    def gcd(j: Int): Int = _integer.gcd(i, j)

    /**
     * Least common multiple
     */
    def lcm(j: Int): Long = _integer.lcm(i, j)

    /**
     * Factorial of the number
     */
    def !(): Long = {
      require(i >= 0, "The term need to be non-negative")
      var fact = 1
      for (k <- 1 to i) fact *= k
      fact
    }

    /**
     * Factors of the number
     */
    def factors(): List[Int] = _integer.factors(i)

  }

}