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
 * - Rotate, then translate dino: rotateAndTranslate() (also includes translateThenRotate solution)
 * - Ex: rotate() function: MatrixUtil.rotate()
 * - Ex: function to draw an N sided polygon: polygonTest()
 * - Ex: translate then rotate: rotateAndTranslate()
 */
object Ch2MainC extends App {

    private val log = Logger("Ch2MainC")


    //rotateDino()
    rotateAndTranslate()
    //polygonTest()

    def rotateDino() : Unit = {
        val angle = Pi / 4.0 //45 degrees
        val dinoRotated = MatrixUtil.rotate(angle, dinoVectors)

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(dinoRotated, lineColor = "red")
    }


    def rotateAndTranslate():Unit = {

        val angle = 5 * Pi / 3
        val t = DenseVector(8.0, 8.0)
        //Rotate, then translate:
        val rt = MatrixUtil.translate(t,
                                            MatrixUtil.rotate(angle, dinoVectors))


        //Translate, then rotate:
        val tr = MatrixUtil.rotate(  angle, MatrixUtil.translate(t, dinoVectors))

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(rt, lineColor = "red")
        drawing.polygon2D(tr, lineColor = "black")
    }

    def polygonTest():Unit = {
        val poly = DrawingUtil.polygon(7)
        new Drawing().polygon2D(poly)
    }
}
