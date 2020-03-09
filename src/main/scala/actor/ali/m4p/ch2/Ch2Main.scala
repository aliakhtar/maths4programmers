package actor.ali.m4p.ch2

import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.numerics.pow
import com.typesafe.scalalogging.Logger

object Ch2Main extends App {

    private val log = Logger("Ch2Main")

    val dinoVectors = DenseMatrix(
        (6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
        (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
        (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
    ).map(_.toDouble)

    //drawDino()
    //ex1()


    def drawDino():Unit = {
        new Drawing().polygon2D(dinoVectors)
    }


    /**
     * Draw the vectors [(x,x**2) for x in range(-10,11)] as points (dots) using the draw function. What
     * is the result?
     */
    def ex1():Unit = {
        val matrix = DenseMatrix.zeros[Double](21, 2)
        (-10 until 11).foreach(x => {
            matrix(x, 0) = x.toDouble
            matrix(x, 1) = pow(x, 2).toDouble

        })

        new Drawing().points2D(matrix)
    }



    def translateDinoVector():Unit = {
        val delta = DenseVector()
    }
}
