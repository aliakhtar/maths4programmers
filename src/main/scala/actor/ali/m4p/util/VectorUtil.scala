package actor.ali.m4p.util

import breeze.linalg._
import breeze.numerics._
import com.typesafe.scalalogging.Logger

object VectorUtil {

    private val log = Logger("VectorArithmetic")

    def add(v1: DenseVector[Double], v2: DenseVector[Double]):DenseVector[Double] = {
        require(v1.length == v2.length)

        val copy = v1.copy
        (0 until v1.length).foreach(i => {
            copy(i) = v1(i) + v2(i)
        })

        copy
    }


    def addAll(vectors: DenseVector[Double]* ):DenseVector[Double] = {
        require(vectors.size >= 2) //make sure at least 2 vectors given

        //make sure all vectors are the same length:
        val lengths = vectors.map(_.length).distinct
        require(lengths.size == 1)

        val length = lengths.head //should be just 1 length for all vectors

        val result = vectors.head.copy

        (0 until length).foreach(col => {
            val sum = vectors.map(v => v(col)).sum
            result(col) = sum
        })

        result
    }

    /**
     * Returns the length of a vector using pythagoras theorem
     */
    def length(vector: DenseVector[Double]):Double = {
        //Length = sqrt(x^2 + y^2)
        require(vector.length == 2)
        val x = vector(0)
        val y = vector(1)
        sqrt( (x * x) + (y * y) )
    }




    def scale(scalar: Double, vector: DenseVector[Double]):DenseVector[Double] = {
        val copy = vector.copy

        (0 until vector.length).foreach(i => {
            copy(i) = vector(i) * scalar
        })

        copy
    }

    def subtract(v1: DenseVector[Double], v2: DenseVector[Double]):DenseVector[Double] = {
        val negated = scale(-1, v2)
        add(v1, negated)
    }


    def distance(v1: DenseVector[Double], v2: DenseVector[Double]):Double = {
        length( subtract(v1, v2) )
    }

    def polarToCartesian(polar: DenseVector[Double]):DenseVector[Double] = {
        val (length, angle) = (polar(0), polar(1))
        val x = length * cos(angle)
        val y = length * sin(angle)
        DenseVector(x, y)
    }

    def cartesianToPolar(cart: DenseVector[Double]):DenseVector[Double] = {
        val (x, y) = (cart(0), cart(1))
        val len = length(cart)
        val angle = atan2(y, x)
        DenseVector(len, angle)
    }

    def rotate(radianAngle: Double, cartesianVector: DenseVector[Double]):DenseVector[Double] = {
        val polar = cartesianToPolar(cartesianVector)
        polar(1) += radianAngle
        polarToCartesian(polar)
    }
}
