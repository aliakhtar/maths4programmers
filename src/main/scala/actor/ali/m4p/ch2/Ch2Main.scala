package actor.ali.m4p.ch2

import actor.ali.m4p.util.Utils
import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.numerics.pow
import com.typesafe.scalalogging.Logger
import org.bouncycastle.jce.provider.PBE.Util


/**
 * Implements exercises, mini projects, and other stuff from Chapter 2.
 *
 * - Drawing basic dinosaur: drawDino()
 * - Ex: Plotting (x, x *x): ex1()
 * - Translating dinoVectors to the left and down: translateDinoVector()
 * - Ex: Adding u + w, u + v, etc: ex2()
 * - Mini proj 1 (taking in any number of vectors): VectorArithmetic#addAl()
 * - Ex: Translate matrix by adding vector: VectorArithmetic#translate()
 */

object Ch2Main extends App {

    private val log = Logger("Ch2Main")

    val dinoVectors = DenseMatrix(
        (6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
        (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
        (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
    ).map(_.toDouble)

    //drawDino()
    //ex1()
    //translateDinoVector()
    ex2()


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
        val delta = DenseVector(-1.5d, -2.5d)
        val dinoVectors2 = DenseMatrix.zeros[Double](dinoVectors.rows, dinoVectors.cols)
        (0 until dinoVectors.rows).foreach(i => {
            val row = dinoVectors(i, ::).inner
            val translated = VectorArithmetic.add(row, delta)
            dinoVectors2(i, 0) = translated(0)
            dinoVectors2(i, 1) = translated(1)
        })

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(dinoVectors2, lineColor = "red")
    }

    /**
     * If the vector u = (-2, 0), the vector v = (1.5, 1.5), and the vector w = (4, 1), what are the results of u + v, v + w,
     * and u + w ? What is the result of u + v + w ?
     */
    def ex2():Unit = {
        val u: DenseVector[Double] = DenseVector(-2.0, 0.0)
        val v: DenseVector[Double] = DenseVector(1.5, 1.5)
        val w: DenseVector[Double] = DenseVector(4.0, 1.0)

        println(s"u + v = ${VectorArithmetic.add(u, v)} ")
        println(s"v + w = ${VectorArithmetic.add(v, w)} ")
        println(s"u + w = ${VectorArithmetic.add(u, w)} ")
        println(s"u + v + w = ${VectorArithmetic.add(VectorArithmetic.add(u, v), w)} ")
    }
}
