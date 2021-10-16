package main.lifewheel

//import android.R

import android.content.res.Configuration
import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var values = mapOf(
        Categories.FAMILY to Data(
            R.id.family_value,
            R.id.family_up,
            R.id.family_down,
            Color.CYAN,
            "Family"
        ),
        Categories.FRIENDS to Data(
            R.id.friends_value,
            R.id.friends_up,
            R.id.friends_down,
            Color.YELLOW,
            "Friends"
        ),
        Categories.LOVE to Data(
            R.id.love_value,
            R.id.love_up,
            R.id.love_down,
            Color.MAGENTA,
            "Love"
        ),
        Categories.SPIRITUALITY to Data(
            R.id.spirituality_value,
            R.id.spirituality_up,
            R.id.spirituality_down,
            Color.RED,
            "Spirituality"
        ),
        Categories.CAREER to Data(
            R.id.career_value,
            R.id.career_up,
            R.id.career_down,
            Color.BLUE,
            "Career"
        ),
        Categories.MONEY to Data(
            R.id.money_value,
            R.id.money_up,
            R.id.money_down,
            Color.GREEN,
            "Money"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // show triangle drawing on image view
        val pieChart: ImageView = findViewById(R.id.pie_chart)
        pieChart.setImageBitmap(drawWedges(values))

        values.forEach { (k, v) ->
            val textView: TextView = findViewById(v.valueID)
            textView.text = v.amount.toString()
            val upButton = findViewById<Button>(v.upID)
            upButton.setOnClickListener {
                if (v.amount < 10) {
                    values.getValue(k).amount++
                    textView.text = values.getValue(k).amount.toString()
                    pieChart.setImageBitmap(drawWedges(values))
                }
            }
            val downButton = findViewById<Button>(v.downID)
            downButton.setOnClickListener {
                if (v.amount > 1) {
                    values.getValue(k).amount--
                    textView.text = values.getValue(k).amount.toString()
                    pieChart.setImageBitmap(drawWedges(values))
                }
            }
        }
    }

    private fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }


    // function to draw triangle on canvas
    fun drawWedges(values: Map<Categories, Data>): Bitmap? {
        val jumpSize = 40F
        val startAngle = 270F
        val bitmap = Bitmap.createBitmap(
            850,
            850,
            Bitmap.Config.ARGB_8888
        )

        // canvas to draw triangle
        val canvas = Canvas(bitmap).apply {
            drawColor(Color.TRANSPARENT)
        }

        val ovals = ArrayList<RectF>()

        repeat(10) { index ->
            val offset = index.toFloat() * jumpSize
            val oval = RectF()
            oval.set(
                offset,
                offset,
                canvas.width.toFloat() - offset,
                canvas.height.toFloat() - offset
            )
            ovals.add(oval)
        }

        ovals.reverse()

        values.forEach { (i, v) ->
            // paint to draw triangle fill color
            val paint = Paint().apply {
                isAntiAlias = true
                color = v.color
                this.strokeWidth = 3F
                style = Paint.Style.FILL
                textSize = 40F
            }

            val path = Path().apply {
                fillType = Path.FillType.EVEN_ODD
                moveTo(canvas.width.toFloat() / 2, canvas.height.toFloat() / 2)
                arcTo(ovals[v.amount - 1], startAngle, 60F, false)
                close()
            }
            // it will draw wedge on canvas
            canvas.drawPath(path, paint)
            // change paint color to draw border



            var outlineColor = Color.BLACK

            if (isDarkModeOn()) {
                outlineColor = Color.WHITE
                print("hello")
            }


            paint.apply {
                color = outlineColor
                style = Paint.Style.STROKE
            }
            // it will draw border on canvas
            canvas.drawPath(path, paint)


            canvas.rotate(270F, canvas.width.toFloat() / 2, canvas.height.toFloat() / 2);

            if (v.amount >= 8) {
                paint.apply {
                    color = Color.BLACK
                }
            }

            val radius =
                (canvas.height / 2) - ((canvas.height / 2) - (v.amount.coerceAtLeast(5) * jumpSize))
            val circle = Path()
            circle.addCircle(
                (canvas.width / 2).toFloat(),
                (canvas.height / 2).toFloat(),
                radius,
                Path.Direction.CW
            )

            val textBounds = Rect()
            paint.getTextBounds(v.label, 0, v.label.length, textBounds)
            val textWidth = paint.measureText(v.label)
            val textHeight = textBounds.height().toFloat()

            val circumference = 2 * Math.PI * radius
            val xOffset = Math.max((((circumference / 6) - textWidth) / 2F).toFloat(), 0F)

            var yOffset = -60F
            if (v.amount >= 8) {
                yOffset = 15F
            }

            canvas.drawTextOnPath(v.label, circle, xOffset, yOffset, paint);


            canvas.rotate(-210F, canvas.width.toFloat() / 2, canvas.height.toFloat() / 2);
        }


        return bitmap
    }
}

enum class Categories {
    FAMILY, FRIENDS, LOVE, SPIRITUALITY, CAREER, MONEY
}

class Data(valueID: Int, upID: Int, downID: Int, color: Int, label: String) {
    val valueID: Int = valueID
    val upID: Int = upID
    val downID: Int = downID
    var amount: Int = 5
    var color: Int = color
    var label: String = label
}