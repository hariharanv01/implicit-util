package com.h2v.impl.utils

object floatimpl {

  /**
   * j power k
   */
  def pow(j: Float, k: Long): Float = {
    require(k >= 0, "The exponent term need to be non-negative")
    (j, k) match {
      case (1, _) | (_, 0) | (0, _) => 1
      case (j, 1)                   => j
      case (j, k) if (k % 2 == 0)   => pow(j * j, k / 2)
      case _                        => pow(j * j, (k + 1) / 2) / j
    }
  }

  implicit class FloatUtil(i: Float) {

    /**
     * nth power
     */
    def ^(n: Long): Float = {
      pow(i, n)
    }

    /**
     * approx equals
     */
    def ~=(o: Float, delta: Float = 0) = (o - i).abs <= delta

    /**
     * way greater than
     */
    def >>>>(o: Float, delta: Float = 1000000): Boolean = (i - o) >= delta

    /**
     * way less than
     */
    def <<<<(o: Float, delta: Float = 1000000) = (o - i) >= delta

  }

}