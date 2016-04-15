package com.h2v.impl.utils

object doubleimpl {

  /**
   * j power k
   */
  def pow(j: Double, k: Long): Double = {
    require(k >= 0, "The exponent term need to be non-negative")
    (j, k) match {
      case (1, _) | (_, 0) | (0, _) => 1
      case (j, 1)                   => j
      case (j, k) if (k % 2 == 0)   => pow(j * j, k / 2)
      case _                        => pow(j * j, (k + 1) / 2) / j
    }
  }

  implicit class DoubleUtil(i: Double) {

    /**
     * nth power
     */
    def ^(n: Long): Double = {
      pow(i, n)
    }

    /**
     * approx equals
     */
    def ~=(o: Double, delta: Double = 0) = (o - i).abs <= delta

    /**
     * way greater than
     */
    def >>>>(o: Double, delta: Double = 1000000): Boolean = (i - o) >= delta

    /**
     * way less than
     */
    def <<<<(o: Double, delta: Double = 1000000) = (o - i) >= delta

  }

}