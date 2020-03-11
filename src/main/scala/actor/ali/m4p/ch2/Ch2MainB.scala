package actor.ali.m4p.ch2

import breeze.linalg._
import breeze.numerics._
import actor.ali.m4p.util.{Drawing, Util, VectorUtil}
import com.typesafe.scalalogging.Logger

/**
 * Contains solutions for Chapter 2, section 2.3
 *
 * - polarToCartesian: VectorUtil#polarToCartesian()
 * - cartesianToPolar: VectorUtil#cartesianToPolar()
 * - Ex: Determine x,y from traveling 8.5 units @ 125 deg: travelPolar()
 * - Print tan of 116.57 = radianTan()
 * - Ex: thousand polars - radians -> thousandPolars()
 */

object Ch2MainB extends App {

    private val log = Logger("Ch2MainB")


    //travelPolar()
    //radianTan()
    //thousandPolars()
    guessAndCheck()

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

    /**
     * The following list comprehension creates 1000 points in polar coordinates.
     * [(cos(5*x*pi/500.0), 2*pi*x/1000.0) for x in range(0,1000)]
     * In Python code, convert them to cartesian coordinates, and connect them in a closed loop with line segments
     * to draw a picture.
     */
    def thousandPolars():Unit = {
        val polars = DenseMatrix.zeros[Double](1000, 2)
        (0 until 1000).foreach(x => {
            val len = cos(5*x*Util.Pi/500.0)
            val angle = 2*Util.Pi*x/1000.0
            polars(x, 0) = len
            polars(x, 1) = angle
        })


        val carts = DenseMatrix.zeros[Double](1000, 2)
        (0 until 1000).foreach(r => {
          val polar = polars(r, ::).inner
          val cart: DenseVector[Double] = VectorUtil.polarToCartesian(polar)
          carts(r, 0) = cart(0)
          carts(r, 1) = cart(1)
        })

        new Drawing().line2D(carts)
    }

    /**
     * Find the angle to get to the point (-2,3) by “guess-and-check”.
     */
    def guessAndCheck():Unit = {
        val vector = DenseVector(-2.0, 3.0)
        val length = VectorUtil.length(vector) //  3.61 ~

        // We need the angle where 3.61 * cos(angle) = -2.0
        // and 3.61 * sin(angle) = 3.0. From hints, we know that the angle is > 90 degrees and < 180 degrees,
        //so it falls between pi and (pi / 2) radians

        val radianFloor = Util.Pi / 2
        val radianCeil = Util.Pi

        val options: DenseVector[Double] = linspace(radianFloor, radianCeil, 1000)

        //println(options)

        val rawOptions: Seq[Double] = (0 until options.length).map(options(_))
        val withLowestDelta = rawOptions.minBy(c => {
            val x = length * cos(c)
            val y = length * sin(c)
            val xDelta = abs( vector(0) - x  )
            val yDelta = abs( vector(1) - y )
            xDelta + yDelta
        })

        log.info(s"Best candidate: $withLowestDelta")
        log.info(s"X: ${length * cos(withLowestDelta)} , expected: ${vector(0)}")
        log.info(s"Y: ${length * sin(withLowestDelta)} , expected: ${vector(1)}")
        log.info(s"Tan: ${tan(withLowestDelta)} , expected: ${vector(1) / vector(0) }")

    }
}
