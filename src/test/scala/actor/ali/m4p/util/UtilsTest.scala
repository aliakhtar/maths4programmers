package actor.ali.m4p.util

import breeze.linalg.DenseMatrix
import com.typesafe.scalalogging.Logger
import org.scalatest.Matchers._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class UtilsTest extends org.scalatest.FunSuite {

    private val log = Logger[UtilsTest]

    test("toRadian") {
        Util.toRadian(45.0) should be(Util.Pi / 4)
        Util.toRadian(90.0) should be(Util.Pi / 2)
        Util.toRadian(180.0) should be(Util.Pi)
        Util.toRadian(360.0) should be(2 * Util.Pi)
    }

    test("fromRadian") {
        Util.fromRadian(Util.Pi / 4) should be(45.0)
        Util.fromRadian(Util.Pi / 2) should be(90.0)
        Util.fromRadian(Util.Pi) should be(180.0)
        Util.fromRadian(2.0 * Util.Pi) should be(360.0)
    }
}
