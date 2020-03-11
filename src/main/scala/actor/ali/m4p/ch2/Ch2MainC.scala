package actor.ali.m4p.ch2

import actor.ali.m4p.util.{Drawing, MatrixUtil, Util, VectorUtil}
import com.typesafe.scalalogging.Logger
import breeze.linalg._
import breeze.numerics
import actor.ali.m4p.util.Util._


/**
 * Contains solutions for Chapter 2, section 2.4
 *
 * - Draw rotated dino by 45 deg: rotateDino()
 */
object Ch2MainC extends App {

    private val log = Logger("Ch2MainC")


    rotateDino()

    def rotateDino() : Unit = {
        val angle = Pi / 4.0 //45 degrees
        val dinoRotated = dinoVectors(*, ::).map(v => {
            val polar = VectorUtil.cartesianToPolar(v)
            polar(1) = polar(1) + angle
            VectorUtil.polarToCartesian(polar)
        })

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(dinoRotated, lineColor = "red")
    }
}
