package com.h2v.impl.utils.glob

package object any {

  implicit class AnyUtil(o: Any) {

    /**
     * Creates a list of the current object with 'n' elements
     */
    def repeat[A](n: Int): List[A] = List.fill(n)(o.asInstanceOf[A])

  }

}