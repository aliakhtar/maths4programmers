package actor.ali.m4p.ch2

import breeze.linalg._
import breeze.plot._
import com.typesafe.scalalogging.Logger

class Drawing {

    private val log = Logger[Drawing]

    val fig: Figure = Figure()
    val plt: Plot = fig.subplot(0)

    def polygon(points: DenseMatrix[Double]):Unit = {
        require(points.cols == 2)
        val x = points(::, 0)
        val y = points(::, 1)


        //draw the points
        plt += plot(x, y, '.',  colorcode = "black")

        //draw the line connecting the points
        plt += plot(x, y, colorcode = "blue")


        fig.refresh()
    }
}
