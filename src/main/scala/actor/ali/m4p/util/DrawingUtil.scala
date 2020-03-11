package actor.ali.m4p.util

import breeze.linalg._
import breeze.numerics._
import actor.ali.m4p.util.Util._
import com.typesafe.scalalogging.Logger

object DrawingUtil {

    private val log = Logger("DrawingUtil")


    /**
     * Create a function regular_polygon(n) which returns cartesian coordinates of a regular n-sided polygon
     * (that is, having all angles and side lengths equal). For instance, polygon(7) could produce vectors defining
     * the following heptagon:
     *
     * Figure 2.67 A regular heptagon, having points at seven evenly spaced angles around the origin.
     *
     * Hint: In this picture I used the vector (1,0) and copies which are rotated by seven evenly spaced angles about
     * the origin.
     */
    def polygon(n: Int):DenseMatrix[Double] = {
        val matrix = DenseMatrix.zeros[Double](n + 1, 2)

        (0 to n).foreach(i => {
            /*
            There are a total of 360 angles in a full circle (2pi), we essentially want to divide those in n
            equal parts and draw lines of length 1 between each angle.

            The formula for angle is 2pi * index / N
            At i = 0 the angle will be 0 and this'll give us the origin vector (1, 0) as in the hints.
            At i = N, the angle will be 360.
             */
            val angle: Double = 2.0 * Util.Pi * i / n
            val polar = DenseVector(1.0, angle)
            val cart = VectorUtil.polarToCartesian(polar)
            log.info(s"Angle @ $i = $angle, ${Util.fromRadian(angle)}, vec: $cart")
            matrix(i, 0) = cart(0)
            matrix(i, 1) = cart(1)
        })

        matrix
    }
}
