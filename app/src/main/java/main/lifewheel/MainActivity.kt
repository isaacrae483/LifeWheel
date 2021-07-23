package main.lifewheel

//import android.R

import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var family: Int = 5
    private var friends: Int = 5
    private var love: Int = 5
    private var spirituality: Int = 5
    private var career: Int = 5
    private var money: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // draw triangle on canvas
        val bitmap = drawTriangle(
            sideLength = 650F,
            yOffsetTopPoint = 150F,
            xOffsetTopPoint = 750F,
            xOffsetLeftPoint = 200F,
            yOffsetBottomHand = 150F,
            strokeWidth = 20F,
            strokeColor = Color.parseColor("#191970"),
            fillColor = Color.parseColor("#D9E650"),
            canvasColor = Color.parseColor("#F2E8D7")
        )

        // show triangle drawing on image view
        val pieChart: ImageView = findViewById(R.id.imageView) as ImageView
        pieChart.setImageBitmap(bitmap)


        val familyTextView: TextView = findViewById(R.id.family_value) as TextView
        familyTextView.text = "$family"
        val familyUpButton = findViewById<Button>(R.id.family_up)
        familyUpButton.setOnClickListener {
            if (family < 10) {
                family++
                familyTextView.text = "$family"
            }
        }
        val familyDownButton = findViewById<Button>(R.id.family_down)
        familyDownButton.setOnClickListener {
            if (family > 0) {
                family--
                familyTextView.text = "$family"
            }
        }

        val friendsTextView: TextView = findViewById(R.id.friends_value) as TextView
        friendsTextView.text = "$family"
        val friendsUpButton = findViewById<Button>(R.id.friends_up)
        friendsUpButton.setOnClickListener {
            if (friends < 10) {
                friends++
                friendsTextView.text = "$friends"
            }
        }
        val friendsDownButton = findViewById<Button>(R.id.friends_down)
        friendsDownButton.setOnClickListener {
            if (friends > 0) {
                friends--
                friendsTextView.text = "$friends"
            }
        }

        val loveTextView: TextView = findViewById(R.id.love_value) as TextView
        loveTextView.text = "$love"
        val loveUpButton = findViewById<Button>(R.id.love_up)
        loveUpButton.setOnClickListener {
            if (love < 10) {
                love++
                loveTextView.text = "$love"
            }
        }
        val loveDownButton = findViewById<Button>(R.id.love_down)
        loveDownButton.setOnClickListener {
            if (love > 0) {
                love--
                loveTextView.text = "$love"
            }
        }

        val spiritualityTextView: TextView = findViewById(R.id.spirituality_value) as TextView
        spiritualityTextView.text = "$spirituality"
        val spiritualityUpButton = findViewById<Button>(R.id.spirituality_up)
        spiritualityUpButton.setOnClickListener {
            if (spirituality < 10) {
                spirituality++
                spiritualityTextView.text = "$spirituality"
            }
        }
        val spiritualityDownButton = findViewById<Button>(R.id.spirituality_down)
        spiritualityDownButton.setOnClickListener {
            if (spirituality > 0) {
                spirituality--
                spiritualityTextView.text = "$spirituality"
            }
        }

        val careerTextView: TextView = findViewById(R.id.career_value) as TextView
        careerTextView.text = "$career"
        val careerUpButton = findViewById<Button>(R.id.career_up)
        careerUpButton.setOnClickListener {
            if (career < 10) {
                career++
                careerTextView.text = "$career"
            }
        }
        val careerDownButton = findViewById<Button>(R.id.career_down)
        careerDownButton.setOnClickListener {
            if (career > 0) {
                career--
                careerTextView.text = "$career"
            }
        }

        val moneyTextView: TextView = findViewById(R.id.money_value) as TextView
        moneyTextView.text = "$money"
        val moneyUpButton = findViewById<Button>(R.id.money_up)
        moneyUpButton.setOnClickListener {
            if (money < 10) {
                money++
                moneyTextView.text = "$money"
            }
        }
        val moneyDownButton = findViewById<Button>(R.id.money_down)
        moneyDownButton.setOnClickListener {
            if (money > 0) {
                money--
                moneyTextView.text = "$money"
            }
        }

    }
}

// function to draw triangle on canvas
fun drawTriangle(
    sideLength : Float = 600F,
    yOffsetTopPoint : Float = 100F,
    xOffsetTopPoint : Float = 600F,
    xOffsetLeftPoint : Float = 200F,
    yOffsetBottomHand : Float = 150F,
    strokeWidth : Float = 15F,
    strokeColor : Int = Color.BLACK,
    fillColor : Int = Color.CYAN,
    canvasColor : Int = Color.LTGRAY
): Bitmap?{
    val bitmap = Bitmap.createBitmap(
        1500,
        850,
        Bitmap.Config.ARGB_8888
    )

    // canvas to draw triangle
    val canvas = Canvas(bitmap).apply {
        drawColor(canvasColor)
    }

    // paint to draw triangle fill color
    val paint = Paint().apply {
        isAntiAlias = true
        color = fillColor
        this.strokeWidth = strokeWidth
        style = Paint.Style.FILL
    }

    // create a path to draw triangle
    val path = Path().apply {
        fillType = Path.FillType.EVEN_ODD
        // draw rectangle on canvas
//        moveTo(xOffsetLeftPoint, canvas.height - yOffsetBottomHand)
        moveTo(0F, canvas.height.toFloat())
        lineTo(xOffsetTopPoint, yOffsetTopPoint)
        lineTo(xOffsetTopPoint  + sideLength, canvas.height - yOffsetBottomHand)
        close()
    }

    // draw path on canvas
    // it will draw triangle fill color
    canvas.drawPath(path, paint)

    // change paint color to draw triangle border
    paint.apply {
        color = strokeColor
        style = Paint.Style.STROKE
    }
    // it will draw triangle border on canvas
    canvas.drawPath(path,paint)

    return bitmap
}