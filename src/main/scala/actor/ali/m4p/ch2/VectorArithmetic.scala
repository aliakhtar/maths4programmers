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
    def distance(vector: DenseVector[Double]):Double = {
        //Distance = sqrt(x^2 + y^2)
        require(vector.length == 2)
        val x = vector(0)
        val y = vector(1)
        sqrt( (x * x) + (y * y) )
    }


    def translate(translation: DenseVector[Double], matrix: DenseMatrix[Double]):DenseMatrix[Double] = {

        val copy = matrix.copy
        (0 until matrix.rows).foreach(r => {
            (0 until matrix.cols).foreach(c => {
                val translated = translation(c) + matrix(r, c)
                copy(r, c) = translated
            })
        })

        copy
    }
}
