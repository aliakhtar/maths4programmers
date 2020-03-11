package actor.ali.m4p.ch2

import actor.ali.m4p.util.{Drawing, DrawingUtil, MatrixUtil, Util, VectorUtil}
import com.typesafe.scalalogging.Logger
import breeze.linalg._
import breeze.numerics._
import actor.ali.m4p.util.Util._


/**
 * Contains solutions for Chapter 2, section 2.4
 *
 * - Draw rotated dino by 45 deg: rotateDino()
 * - Rotate, then translate dino: rotateThenTranslate()
 * - Ex: rotate() function: MatrixUtil.rotate()
 * - Ex: function to draw an N sided polygon: polygonTest()
 */
object Ch2MainC extends App {

    private val log = Logger("Ch2MainC")


    //rotateDino()
    //rotateThenTranslate()
    polygonTest()

    def rotateDino() : Unit = {
        val angle = Pi / 4.0 //45 degrees
        val dinoRotated = MatrixUtil.rotate(angle, dinoVectors)

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(dinoRotated, lineColor = "red")
    }


    def rotateThenTranslate():Unit = {
        val result = MatrixUtil.translate(DenseVector(8.0, 8.0),
                                            MatrixUtil.rotate(5 * Pi / 3, dinoVectors))

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(result, lineColor = "red")
    }

    def polygonTest():Unit = {
        val poly = DrawingUtil.polygon(7)
        new Drawing().polygon2D(poly)
    }
}
