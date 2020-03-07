package actor.ali.m4p.ch2

import breeze.linalg._
import breeze.plot._
import com.typesafe.scalalogging.Logger

class Drawing {

    private val log = Logger[Drawing]

    val fig: Figure = Figure()
    val plt: Plot = fig.subplot(0)




    def points2D(points: DenseMatrix[Double], color:String = "black", style: Char = '.'):Unit = {
        require(points.cols == 2)
        val x = points(::, 0)
        val y = points(::, 1)
        //draw the points
        plt += plot(x, y, style,  colorcode = color)
        fig.refresh()
    }

    def line2D(points: DenseMatrix[Double], color: String = "blue"):Unit = {
        val x = points(::, 0)
        val y = points(::, 1)

        //draw the line connecting the points
        plt += plot(x, y, colorcode = color)
        fig.refresh()
    }

    def polygon2D(points: DenseMatrix[Double], pointColor:String = "black", lineColor: String = "blue"):Unit = {
        points2D(points, pointColor)
        line2D(points, lineColor)
        //Connect the tip and tail, i.e first and last points
        val tailToTop = points(Seq(0, -1), ::)
        val tttX = tailToTop(::, 0)
        val tttY = tailToTop(::, 1)

        plt += plot(tttX, tttY, colorcode = lineColor)

        fig.refresh()
    }

}
