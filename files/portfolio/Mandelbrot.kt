import java.awt.Canvas
import java.awt.Graphics
import javax.swing.JFrame
import java.awt.image.BufferedImage
import java.awt.Color
import javax.swing.WindowConstants
import java.awt.event.MouseMotionListener
import java.awt.event.MouseEvent
import java.awt.Point
import java.awt.Graphics2D
import java.awt.BasicStroke

class Mandelbrot : Canvas(), MouseMotionListener {

	companion object Constants {
//		const val FILENAME = "C:\\mandelbrot.PNG"
		const val FILENAME = "C:\\jula_fractal.PNG"

		const val WINDOW_SIZE_MULTIPLYER = 2
		const val WIDTH: Int = (900 * WINDOW_SIZE_MULTIPLYER).toInt()
		const val HEIGHT: Int = (650 * WINDOW_SIZE_MULTIPLYER).toInt()
		const val SCALER: Int = HEIGHT
		const val SIZE = 2.3

		const val MAX_ITERATIONS = 10000

		const val RE: Double = -0.79
		const val IM: Double = -0.159

//		const val RE: Double = 0.0
//		const val IM: Double = 0.0
	}
	
	

	var running = true
	public var done = false
	var image: BufferedImage = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
	var index = 0

	// Path taken from mouse location
	var path: MutableList<Point> = mutableListOf()

	init {
		addMouseMotionListener(this)
	}

	override fun paint(g: Graphics) {

		val beginTime = System.currentTimeMillis()

		if (running) {
			do {
				repeat(50) {
					if (running) {
						draw()
					}
				}
			} while (System.currentTimeMillis() - beginTime < 16)
		}

		g.drawImage(
			image, 0, 0, WIDTH / WINDOW_SIZE_MULTIPLYER.toInt(),
			HEIGHT / WINDOW_SIZE_MULTIPLYER.toInt(), this
		)

		// drawPath(g)

		if (!running && !done) {
			println("Done.")

			println("Saving image...")
			javax.imageio.ImageIO.write(image, "PNG", java.io.File(FILENAME))
			println("Saved image to " + FILENAME + ".")
			done = true
		}

	}

	override fun update(g: Graphics) {

		//if (running) {
		paint(g)
		//}

	}

	fun draw() {

		var row: Int = index / WIDTH
		var col: Int = index % WIDTH

		index++
		if (index >= WIDTH * HEIGHT / 2) {
			running = false
		}

		//drawPixelMandelbrot(row, col)
		drawPixelJulia(row, col)

	}

	fun drawPixelMandelbrot(row: Int, col: Int) {

		var re = ((col.toDouble() - WIDTH * 0.5) / SCALER) * SIZE
		var im = ((row.toDouble() - HEIGHT * 0.5) / SCALER) * SIZE * -1

		// Get value from function
		//var iterations = julia(re, im)
		var iterations = mandelbrot(re, im)

		// Set color
		if (iterations >= MAX_ITERATIONS) {
			image.setRGB(col, row, Color.BLACK.getRGB())
		} else {
			var x = iterations.toDouble()
			x = Math.log(x / 5000) / Math.log(500.0)
			var color: Color = Color.getHSBColor(x.toFloat(), 1f, 1f)
			image.setRGB(col, row, color.getRGB())
			image.setRGB(col, HEIGHT - row - 1, color.getRGB())
		}
	}

	fun drawPixelJulia(row: Int, col: Int) {

		var re = ((col.toDouble() - WIDTH * 0.5) / SCALER) * SIZE
		var im = ((row.toDouble() - HEIGHT * 0.5) / SCALER) * SIZE * -1

		// Get value from function
		//var iterations = julia(re, im)
		var iterations = julia(re, im)

		// Set color
		if (iterations >= MAX_ITERATIONS) {
			image.setRGB(col, row, Color.BLACK.getRGB())
		} else {
			var x = iterations.toDouble()
			x = -Math.log((x + 1) / 20000) / Math.log(200.0) + 0.9
			var color: Color = Color.getHSBColor(x.toFloat(), 1f, 1f)
			image.setRGB(col, row, color.getRGB())
			image.setRGB(WIDTH - col - 1, HEIGHT - row - 1, color.getRGB())
		}
	}

	fun mandelbrot(re: Double, im: Double): Int {

//		var z_re: Double = RE
//		var z_im: Double = IM

		var z_re: Double = 0.0
		var z_im: Double = 0.0

		if (RE == 0.0 && IM == 0.0) {
			// Optimize by checking for the main cardioid
			var q = (re - 0.25) * (re - 0.25) + im * im
			if (q * (q + (re - 0.25)) <= 0.25 * im * im) {
				return MAX_ITERATIONS
			}
			// Optimize by checking for period-2 bulb
			else if ((re + 1) * (re + 1) + im * im < 0.0625) {
				return MAX_ITERATIONS
			}
			// check for period-3 bulbs?
			else if ((re + 0.124) * (re + 0.124) + (im - 0.7421875) * (im - 0.7421875) < (2.93 * 2.93 / (32 * 32))) {
				return MAX_ITERATIONS
			} else if ((re + 0.124) * (re + 0.124) + (im + 0.7421875) * (im + 0.7421875) < (2.93 * 2.93 / (32 * 32))) {
				return MAX_ITERATIONS
			}
		}

		var iteration = 0

		while (z_re * z_re + z_im * z_im <= 2 * 2 && iteration < MAX_ITERATIONS) {

			var temp = z_re * z_re - z_im * z_im + re
			z_im = 2 * z_re * z_im + im
			z_re = temp

			iteration++

		}

		return iteration
	}

	fun julia(real: Double, imaginary: Double): Int {

		// Constant
		var re: Double = RE
		var im: Double = IM

		// point
		var z_re: Double = real
		var z_im: Double = imaginary

		var iteration = 0

		while (z_re * z_re + z_im * z_im <= 2 * 2 && iteration < MAX_ITERATIONS) {

			var temp = z_re * z_re - z_im * z_im + re
			z_im = 2 * z_re * z_im + im
			z_re = temp

			iteration++

		}

		return iteration
	}

	public fun isDone(): Boolean {
		return done
	}

	fun drawPath(g: Graphics) {
		
		g.setColor(Color.RED)
		(g as Graphics2D).setStroke(BasicStroke(1.5f))
		
		for (i in 1..path.lastIndex) {

			val p1 = path.get(i - 1)
			val p2 = path.get(i)

			g.drawLine(p1.getX().toInt(), p1.getY().toInt(), p2.getX().toInt(), p2.getY().toInt())
		}
	}

	override fun mouseDragged(e: MouseEvent) {}
	override fun mouseMoved(e: MouseEvent) {
		var x = e.getX()
		var y = e.getY()

		path.add(Point(x, y))
	}

}

fun main(args: Array<String>) {

	var frame: JFrame = JFrame("Fractal drawer")
	var canvas: Mandelbrot = Mandelbrot()
	val SCALE = Mandelbrot.WINDOW_SIZE_MULTIPLYER
	canvas.setSize((Mandelbrot.WIDTH / SCALE).toInt(), (Mandelbrot.HEIGHT / SCALE).toInt())
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
	frame.add(canvas)
	frame.pack()
	frame.setVisible(true)

	var running = true

	while (running) {
		canvas.repaint()
	}

}