package actor.ali.m4p.ch2

import java.util.concurrent.atomic.AtomicInteger

import actor.ali.m4p.util.{Drawing, MatrixUtil, Util, VectorUtil}
import breeze.linalg._
import breeze.numerics._
import com.typesafe.scalalogging.Logger


/**
 * Implements exercises, mini projects, and other stuff for Chapter 2, section 2.1 + 2.2
 *
 * - Drawing basic dinosaur: drawDino()
 * - Ex: Plotting (x, x *x): ex1()
 * - Translating dinoVectors to the left and down: translateDinoVector()
 * - Ex: Adding u + w, u + v, etc: ex2()
 * - Mini proj: (taking in any number of vectors): VectorUtil#addAl()
 * - Ex: Translate matrix by adding vector: MatrixUtil#translate()
 * - Mini proj: Hundred copies of dino: drawHundredDinos()
 * - Ex: Longest length of dinoVector: ex3()
 * - Ex: Write method to scale Vector: VectorUtil#scale()
 * - Ex: Draw points between range: vectorPossibilities()
 * - Ex: Subtraction: VectorUtil#subtract()
 * - Ex: Distance: VectorUtil#distance()
 * - Ex: Perimeter: VectorUtil#perimeter, dinoPerimeter()
 * - Mini proj: Search for a vector w/ matching displacement: searchDisplacement()
 */

object Ch2MainA extends App {

    private val log = Logger("Ch2Main")

    val dinoVectors = DenseMatrix(
        (6,4), (3,1), (1,2), (-1,5), (-2,5), (-3,4), (-4,4),
        (-5,3), (-5,2), (-2,2), (-5,1), (-4,0), (-2,1), (-1,0), (0,-3),
        (-1,-4), (1,-4), (2,-3), (1,-2), (3,-1), (5,1)
    ).map(_.toDouble)

    drawDino()
    //ex1()
    //translateDinoVector()
    //ex2()
    //drawHundredDinos()
    //ex3()
    //vectorPossibilities()
    //dinoPerimeter()
    //searchDisplacement()


    def drawDino():Unit = {
        new Drawing().polygon2D(dinoVectors)
    }


    /**
     * Draw the vectors [(x,x**2) for x in range(-10,11)] as points (dots) using the draw function. What
     * is the result?
     */
    def ex1():Unit = {
        val matrix = DenseMatrix.zeros[Double](21, 2)
        (-10 until 11).foreach(x => {
            matrix(x, 0) = x.toDouble
            matrix(x, 1) = pow(x, 2).toDouble

        })

        new Drawing().points2D(matrix)
    }



    def translateDinoVector():Unit = {
        val delta = DenseVector(-1.5d, -2.5d)
        val dinoVectors2 = DenseMatrix.zeros[Double](dinoVectors.rows, dinoVectors.cols)
        (0 until dinoVectors.rows).foreach(i => {
            val row = dinoVectors(i, ::).inner
            val translated = VectorUtil.add(row, delta)
            dinoVectors2(i, 0) = translated(0)
            dinoVectors2(i, 1) = translated(1)
        })

        val drawing = new Drawing()
        drawing.polygon2D(dinoVectors)
        drawing.polygon2D(dinoVectors2, lineColor = "red")
    }

    /**
     * If the vector u = (-2, 0), the vector v = (1.5, 1.5), and the vector w = (4, 1), what are the results of u + v, v + w,
     * and u + w ? What is the result of u + v + w ?
     */
    def ex2():Unit = {
        val u: DenseVector[Double] = DenseVector(-2.0, 0.0)
        val v: DenseVector[Double] = DenseVector(1.5, 1.5)
        val w: DenseVector[Double] = DenseVector(4.0, 1.0)

        println(s"u + v = ${VectorUtil.add(u, v)} ")
        println(s"v + w = ${VectorUtil.add(v, w)} ")
        println(s"u + w = ${VectorUtil.add(u, w)} ")
        println(s"u + v + w = ${VectorUtil.add(VectorUtil.add(u, v), w)} ")
    }


    /**
     * Write a function using vector addition to show 100 simultaneous and non-overlapping copies of the
     * dinosaur.
     *
     * Note: This will print a bunch of exceptions about ConcurrentModificationExceptions, just ignore them as the
     * drawings do seem to get drawn correctly regardless. My guess is, drawing.refresh() being called each time
     * causes these exceptions
     */
    def drawHundredDinos():Unit = {

        //Build 100 copies of the translation vectors - just use the values given in the solution for these:
        val translations = for (x <- (-5 until 5);
                                y <- (-5 until 5))
                            yield DenseVector(12.0 * x, 10.8 * y)

        val dinos = translations.map(t => MatrixUtil.translate(t, dinoVectors))

        val drawing = new Drawing
        val counter = new AtomicInteger()
        dinos.foreach(vectors => {
            drawing.line2D(vectors) //much better perf than polygon2D since no connecting points
            val done = counter.incrementAndGet()
            log.info(s"$done / ${dinos.size} vectors drawn")
        })
    }


    /**
     * What vector in the dino_vectors list has the longest length? Use the length function we wrote to
     * compute the answer quickly
     */
    def ex3():Unit = {
        val vectors = Util.matrixRows(dinoVectors)
        val longestLength =  vectors.maxBy(v => VectorUtil.length(v))
        log.info(s"Longest vector: $longestLength, length: ${VectorUtil.length(longestLength)}")
    }

    /**
     * Suppose u = (-1,1) and v = (1,1) and suppose r and s are real numbers. Specifically, let’s assume -1 < r < 1
     * and -3 < s < 3.
     * Where are the possible points on the plane where the vector r ∙ u + s ∙ v could end up?
     *
     * Note: From some reason, this prints the points going up and to the right, but the book shows them down
     * and to the left. I think its just a difference in plotting between Matlin & Breeze-viz. The actual points
     * are accurate - the same min + max points appear to be drawn.
     */
    def vectorPossibilities():Unit = {
        val u = DenseVector(-1.0, 1.0)
        val v = DenseVector(1.0, 1.0)

        val fig = new Drawing
        val matrix = DenseMatrix.zeros[Double](500, 2)
        (0 until 500).foreach(i => {
            val r = Util.random.between(-1.0 + 0.1, 1.0)
            val s = Util.random.between(-3.0 + 0.1, 3.0)
            val uPlusV = VectorUtil.add( VectorUtil.scale(r, u) , VectorUtil.scale(s, v))

            matrix(i, 0) = uPlusV(0)
            matrix(i, 1) = uPlusV(1)
        })

        println(matrix)
        fig.points2D(matrix)
    }

    def dinoPerimeter():Unit = {
        val perimeter = MatrixUtil.perimeter( dinoVectors )
        log.info(s"Dino perimeter: $perimeter")
    }

    /**
     * Let u be the vector (1,2). Suppose there is another vector, v, with positive integer coordinates (n, m) such that
     * n > m, and having distance 13 from u. What is the displacement from u to v? Hint: you can use Python to
     * search for the vector v.
     */
    def searchDisplacement():Unit = {

        //Note: There seems to be a typo in the question. In the solution, u is taken as (1.0, -1.0) instead of
        //(1.0, 2.0), so lets use the solution value here
        val u = DenseVector(1.0, -1.0)
        val uToVDistance = 13.0

        var v: Option[DenseVector[Double]] = None
        var n: Double = -1

        while (v.isEmpty && n <= 20) {
            n = n + 1d
            var m: Double = -1
            while (v.isEmpty && m < n) {
                m = m + 1

                val candidate = DenseVector(n, m)
                val distance = VectorUtil.distance(candidate, u)

                log.debug(s"Trying $candidate, distance: $distance")
                if (distance == uToVDistance && candidate(0) > candidate(1))
                    v = Some(candidate)
            }
        }

        log.info(s"Found candidate: ${v.get}, Displacement: ${VectorUtil.subtract(v.get, u)}, distance: ${VectorUtil.distance(v.get, u)}")
    }
}
