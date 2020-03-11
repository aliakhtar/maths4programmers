package actor.ali.m4p.util

import com.typesafe.scalalogging.Logger
import breeze.linalg._
import breeze.numerics._

object MatrixUtil {

    private val log = Logger("MatrixUtil")


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


    /**
     * Returns the sum of distances from each vector to the next, including the distance from the last vector to the
     * first.
     */
    def perimeter(matrix: DenseMatrix[Double]):Double = {

        val rows = Util.matrixRows(matrix)
        val lastIndex = rows.indices.last
        rows.zipWithIndex.map(t => {
            val row = t._1

            val i = t._2
            val nextRowIndex = if (i < lastIndex) i + 1 else 0
            val nextRow = rows(nextRowIndex)

            VectorUtil.distance(row, nextRow)
        }).sum
    }


    def rotate(radianAngle: Double, matrix: DenseMatrix[Double]):DenseMatrix[Double] = {
        matrix(*, ::).map(VectorUtil.rotate(radianAngle, _))
    }
}
