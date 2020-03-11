package actor.ali.m4p.util

import breeze.linalg._
import breeze.plot._
import com.typesafe.scalalogging.Logger

class Drawing {

    private val log = Logger[Drawing]

    private val fig: Figure = Figure()
    private var pltIndex = 0
    private var plt: Plot = fig.subplot(pltIndex)



    def createNewPlot():Unit = {
        pltIndex += 1
        plt = fig.subplot(pltIndex)
    }

    def refresh():Unit = {
        fig.refresh()
    }


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

    def line(x: Double, y: Double, color: String = "blue"):Unit = {
        val xV = DenseVector(0.0, x)
        val yV = DenseVector(0.0, y)
        plt += plot(xV, yV, colorcode = color)
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
