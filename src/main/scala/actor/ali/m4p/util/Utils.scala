package actor.ali.m4p.util

import breeze.linalg.{DenseMatrix, DenseVector, Transpose}

import scala.reflect.runtime.universe._
import com.typesafe.scalalogging.Logger

import scala.reflect.ClassTag

object Utils {

    private val log = Logger("Utils")


    /**
     * Returns the rows in a matrix
     */
    def matrixRows[T](matrix: DenseMatrix[T]):Seq[DenseVector[T]] = {
        (0 until matrix.rows).map(i => {
            val row: Transpose[DenseVector[T]] = matrix(i, ::)
            row.inner
        })
    }
}
