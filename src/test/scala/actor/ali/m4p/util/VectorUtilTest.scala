package actor.ali.m4p.util

import breeze.linalg.{DenseMatrix, DenseVector}
import com.typesafe.scalalogging.Logger
import org.scalatest.Matchers._

class VectorUtilTest extends org.scalatest.FunSuite {

    private val log = Logger[VectorUtilTest]

    test("add") {
        val v1 = DenseVector(1.0, 2.0)
        val v2 = DenseVector(0.1, 0.2)

        val result = VectorUtil.add(v1, v2)

        result(0) should be(1.1)
        result(1) should be(2.2)
    }

    test("length") {
        val vec: DenseVector[Double] = DenseVector(4.0, 3.0)
        VectorUtil.length(vec) should be(5)
    }

    test("addAll") {
        val a = DenseVector(1.0, 2.0)
        val b = DenseVector(2.0, 4.0)
        val c = DenseVector(3.0, 6.0)
        val d = DenseVector(4.0, 8.0)
        val sum = VectorUtil.addAll(a, b, c, d)
        sum(0) should be (10.0)
        sum(1) should be(20.0)
        sum.length should be(2)
    }


    test("scaleVector") {
        val result = VectorUtil.scale(2, DenseVector(2.0, 4.0))
        result(0) should be(4.0)
        result(1) should be(8.0)
    }

    test("subtract") {
        val v1 = DenseVector(3.0, 4.0)
        val v2 = DenseVector(1.0, 2.0)
        val result =  VectorUtil.subtract(v1, v2)
        result should be(DenseVector(2.0, 2.0))
    }


    test("distance") {

        //v1 - v2 should be (4.0, 3.0). The length of that will be 5.0
        val v1 = DenseVector(8.0, 5.0)
        val v2 = DenseVector(4.0, 2.0)

        VectorUtil.distance(v1, v2) should be(5.0)
    }

    test("polarToCartesian") {
        val angle = Util.toRadian(37.0)
        val polar = DenseVector(5.0, angle)
        val cart = VectorUtil.polarToCartesian(polar)
        println(cart)
        math.round(cart(0)) should be(4)
        math.round(cart(1)).toInt should be(3)
    }

    test("cartToPolar") {
        val cart = DenseVector(4d, 3d)
        val polar = VectorUtil.cartesianToPolar(cart)
        println(polar)
        polar(0) should be(5.0)
        math.round(Util.fromRadian(polar(1))) should be(37.0)
    }
}
