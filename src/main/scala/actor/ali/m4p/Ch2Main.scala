package actor.ali.m4p

import actor.ali.m4p.ch2.Drawing
import breeze.linalg.DenseMatrix
import com.typesafe.scalalogging.Logger

object Ch2Main extends App {

    private val log = Logger("Ch2Main")

    val dinoVectors = DenseMatrix(
        (6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
        (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
        (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
    ).map(_.toDouble)

    new Drawing().polygon(dinoVectors)
}
