package actor.ali.m4p.ch2

import breeze.linalg.DenseVector
import com.typesafe.scalalogging.Logger
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class VectorArithmeticTest extends org.scalatest.FunSuite {

    private val log = Logger[VectorArithmeticTest]

    test("add") {
        val v1 = DenseVector(1.0, 2.0)
        val v2 = DenseVector(0.1, 0.2)

        val result = VectorArithmetic.add(v1, v2)

        result(0) should be(1.1)
        result(1) should be(2.2)
    }

    test("distance") {
        val vec: DenseVector[Double] = DenseVector(4.0, 3.0)
        VectorArithmetic.distance(vec) should be(5)
    }

    test("addAll") {
        val a = DenseVector(1.0, 2.0)
        val b = DenseVector(2.0, 4.0)
        val c = DenseVector(3.0, 6.0)
        val d = DenseVector(4.0, 8.0)
        val sum = VectorArithmetic.addAll(a, b, c, d)
        sum(0) should be (10.0)
        sum(1) should be(20.0)
        sum.length should be(2)
    }

}
