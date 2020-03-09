package actor.ali.m4p.ch2

import breeze.linalg._
import breeze.numerics._
import com.typesafe.scalalogging.Logger

object VectorArithmetic {

    private val log = Logger("VectorArithmetic")

    def add(v1: DenseVector[Double], v2: DenseVector[Double]):DenseVector[Double] = {
        require(v1.length == v2.length)

        val copy = v1.copy
        (0 until v1.length).foreach(i => {
            copy(i) = v1(i) + v2(i)
        })

        copy
    }

    /**
     * Returns the length of a vector using pythagoras theorem
     */
    def distance(vector: DenseVector[Double]):Double = {
        //Distance = sqrt(x^2 + y^2)
        require(vector.length == 2)
        val x = vector(0)
        val y = vector(1)
        sqrt( (x * x) + (y * y) )
    }
}
