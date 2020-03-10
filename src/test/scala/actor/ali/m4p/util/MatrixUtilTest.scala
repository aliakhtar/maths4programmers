package actor.ali.m4p.util

import breeze.linalg.{DenseMatrix, DenseVector}
import com.typesafe.scalalogging.Logger
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class MatrixUtilTest extends org.scalatest.FunSuite {

    private val log = Logger[MatrixUtilTest]


    test("translate") {
        val translation = DenseVector(1.0, 1.0)
        val orig = DenseMatrix((0.0,0.0), (0.0, 1.0), (-3.0, -3.0) )

        val translated = MatrixUtil.translate(translation, orig)

        //row 1
        translated(0, 0) should be(1.0)
        translated(0, 1) should be(1.0)

        //row 2
        translated(1, 0) should be(1.0)
        translated(1, 1) should be(2.0)

        //row 3
        translated(2, 0) should be(-2.0)
        translated(2, 1) should be(-2.0)

        translated.rows should be(3)
        translated.cols should be(2)
    }

}
