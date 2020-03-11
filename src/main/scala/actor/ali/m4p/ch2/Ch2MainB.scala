package actor.ali.m4p.ch2

import breeze.linalg._
import breeze.numerics._
import actor.ali.m4p.util.{Drawing, Util}
import com.typesafe.scalalogging.Logger

/**
 * Contains solutions for Chapter 2, section 2.3
 *
 * - polarToCartesian: VectorUtil#polarToCartesian()
 * - cartesianToPolar: VectorUtil#cartesianToPolar()
 * - Ex: Determine x,y from traveling 8.5 units @ 125 deg: travelPolar()
 * - Print tan of 116.57 = radianTan()
 */

object Ch2MainB extends App {

    private val log = Logger("Ch2MainB")


    //travelPolar()
    radianTan()

    /**
     * Suppose I travel 8.5 units from the origin at an angle of 125°, measured counterclockwise from the positive x
     * axis. Given that sin(125°) = 0.819 and cos(125°) = -0.574, what are my final coordinates? Draw a picture to
     * show the angle and path traveled.
     */
    def travelPolar():Unit = {
        val len = 8.5
        val x: Double = len * -0.574
        val y: Double = len * 0.819
        new Drawing().line(x, y)
    }

    /**
     * What is 116.57° in radians? Use Python to compute the tangent of this angle, and confirm that it is close to -
     * 2 as we saw above.
     */
    def radianTan():Unit = {
        val degrees = 116.57
        val radians = Util.toRadian(degrees)
        val t = tan(radians)
        println(s"Tan: $t")
    }
}
