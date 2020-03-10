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
}
