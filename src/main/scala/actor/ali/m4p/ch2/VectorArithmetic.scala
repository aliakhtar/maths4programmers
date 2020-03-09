package actor.ali.m4p.ch2

import breeze.linalg.DenseVector
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
}
