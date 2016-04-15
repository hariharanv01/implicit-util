# implicit-util

A Scala library for adding utility methods to Strings, Numeric types, List, etc implicitly.

It provides various functions for shuffle, lcm, gcd, ~= , random, sampling, factors, factorial and many more.

### Implicit utils few examples

  ```scala
      // List Implicits

        import com.h2v.impl.utils.listimpl._
      
        val list = List(1, 2, 3, 4, 5, 6)               //> list  : List[Int] = List(1, 2, 3, 4, 5, 6)
      
        shuffle(list)                                   //> res0: List[Int] = List(4, 3, 6, 5, 2, 1)
      
        ~list                                           //> res1: List[Int] = List(6, 5, 4, 3, 2, 1)
      
        list U (List(7, 9))                             //> res2: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 9)
      
        list & (List(2, 3))                             //> res3: List[Int] = List(2, 3)
      
        list sample 2                                   //> res4: List[Int] = List(4, 5)
      
        list >>= (i => List(2 * i)) >>= (i => List(i + 8))
                                                        //> res5: List[Int] = List(10, 12, 14, 16, 18, 20)
      
        list rand                                       //> res6: Int = 1
      
        list >> 3                                       //> res7: List[Int] = List(4, 5, 6, 1, 2, 3)
        
      
        // Integer Implicits
      
        import com.h2v.impl.utils.intimpl._
      
        succ(Int.MaxValue)                              //> res8: Option[Int] = None
      
        succ(100)                                       //> res9: Option[Int] = Some(101)
      
        10 gcd 55                                       //> res10: Int = 5
      
        gcd(10, 55)                                     //> res11: Int = 5
      
        lcm(20, 30)                                     //> res12: Long = 60
      
      	5 !                                       //> res13: Long = 120
      	
      	10 factors                                //> res14: List[Int] = List(1, 2, 5, 10)
      	
      	5 ^^                                      //> res15: Int = 32
      	
      	2 ** 8                                    //> res16: Int = 256
      		
      	10 ~= 11                                  //> res17: Boolean = false
      
      	10 ~= (11, 1)                             //> res18: Boolean = true
      	
      	10 ~= (12, 1)                             //> res19: Boolean = false
 
