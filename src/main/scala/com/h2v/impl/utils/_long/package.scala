package com.h2v.impl.utils.num

import scala.collection.mutable.ListBuffer
import scala.math.ceil
import scala.math.sqrt

package object _long {

  /**
   * Overflow safe predecessor
   */
  def pred(i: Long): Option[Long] = if (i > Long.MinValue) Some(i - 1) else None

  /**
   * Overflow safe successor
   */
  def succ(i: Long): Option[Long] = if (i < Long.MaxValue) Some(i + 1) else None

  /**
   * Greatest common divisor
   */
  def gcd(i: Long, j: Long): Long = {
    require(i >= 0 && j >= 0, "Both the terms need to be non-negative")
    if (i % j == 0) j else gcd(j % i, i)
  }

  /**
   * Least common multiple
   */
  def lcm(i: Long, j: Long): Long = (i * j) / gcd(i, j)

  /**
   * power of the number
   */
  def pow(j: Long, k: Long): Long = {
    require(k >= 0, "The exponent term need to be non-negative")
    (j, k) match {
      case (1, _) | (_, 0) | (0, _) => 1
      case (j, 1)                   => j
      case (j, k) if (k % 2 == 0)   => pow(j * j, k / 2)
      case _                        => pow(j * j, (k + 1) / 2) / j
    }
  }

  /**
   * Factors of the number
   */
  def factors(i: Long) = {
    require(i >= 0, "The term need to be non-negative")
    val lb = ListBuffer[Long]()
    for (j <- 1 to ceil(sqrt(i)).toInt if (i % j == 0)) {
      lb += j
    }
    lb += i
    lb toList
  }

  implicit class LongUtil(i: Long) {

    /**
     * 2th power of the current number
     */
    def ^^() = 1 << i

    /**
     * power of the number
     */
    def ^(n: Long): Long = {
      pow(i, n)
    }

    /**
     * approx equal to
     */
    def ~=(o: Long, delta: Long = 0) = (o - i).abs <= delta

    /**
     * way greater than
     */
    def >>>>(o: Long, delta: Long = 1000000): Boolean = (i - o) >= delta

    /**
     * way less than
     */
    def <<<<(o: Long, delta: Long = 1000000) = (o - i) >= delta

    /**
     * Greatest common divisor
     */
    def gcd(j: Long): Long = _long.gcd(i, j)

    /**
     * Least common multiple
     */
    def lcm(j: Long): Long = _long.lcm(i, j)

    /**
     * Factorial of the number
     */
    def !(): Long = {
      require(i >= 0, "The term need to be non-negative")
      var fact = 1
      for (k <- 1 to i.toInt) fact *= k
      fact
    }

    /**
     * Factors of the number
     */
    def factors(): List[Long] = _long.factors(i)

  }

}