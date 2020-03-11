package actor.ali.m4p.util

import breeze.linalg.{DenseMatrix, DenseVector, Transpose}

import scala.reflect.runtime.universe._
import com.typesafe.scalalogging.Logger

import scala.reflect.ClassTag
import scala.util.Random

object Util {

    private val log = Logger("Utils")

    val Pi: Double = breeze.numerics.constants.Pi

    val random = new Random()

    val dinoVectors =  DenseMatrix(
        (6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
        (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
        (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
    ).map(_.toDouble)


    /**
     * Returns the rows in a matrix
     */
    def matrixRows[T](matrix: DenseMatrix[T]):Seq[DenseVector[T]] = {
        (0 until matrix.rows).map(i => {
            val row: Transpose[DenseVector[T]] = matrix(i, ::)
            row.inner
        })
    }

    def toRadian(angle: Double):Double = (angle * Pi) / 180

    def fromRadian(radian: Double):Double = (radian * 180) / Pi
}
