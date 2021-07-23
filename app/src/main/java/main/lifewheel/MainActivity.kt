package main.lifewheel

//import android.R

import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2


class MainActivity : AppCompatActivity() {
    private var family: Int = 5
    private var friends: Int = 5
    private var love: Int = 5
    private var spirituality: Int = 5
    private var career: Int = 5
    private var money: Int = 5
    // 270, 330, 30, 90, 150, 210

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // draw wedge on canvas
        val familyBitmap = drawWedge(
            startAngle = 270F,
            fillColor = Color.parseColor("#D9E650")
        )

        // show triangle drawing on image view
        val familyWedge: ImageView = findViewById(R.id.family_wedge)
        familyWedge.setImageBitmap(familyBitmap)

//        val friendsBitmap = drawWedge(
//            startAngle = 330F,
//            fillColor = Color.parseColor("#D9E650")
//        )
//
//        val friendsWedge: ImageView = findViewById(R.id.friends_wedge)
//        familyWedge.setImageBitmap(friendsBitmap)


        val familyTextView: TextView = findViewById(R.id.family_value)
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
fun drawWedge(
    startAngle : Float,
    strokeWidth : Float = 5F,
    strokeColor : Int = Color.BLACK,
    fillColor : Int = Color.CYAN
): Bitmap?{
    val bitmap = Bitmap.createBitmap(
        850,
        850,
        Bitmap.Config.ARGB_8888
    )

    // canvas to draw triangle
    val canvas = Canvas(bitmap).apply {
        drawColor(Color.TRANSPARENT)
    }

    // paint to draw triangle fill color
    val paint = Paint().apply {
        isAntiAlias = true
        color = fillColor
        this.strokeWidth = strokeWidth
        style = Paint.Style.FILL
    }

    val radius = 20f
    val oval = RectF()
    oval.set(10F, 10F, canvas.width.toFloat() - 10, canvas.height.toFloat() - 10)

    val oval1 = RectF()
    oval1.set(100F, 100F, canvas.width.toFloat() - 100, canvas.height.toFloat() - 100)

    // create a path to draw triangle
    val path = Path().apply {
        fillType = Path.FillType.EVEN_ODD
        moveTo(canvas.width.toFloat() / 2, canvas.height.toFloat() / 2)
        arcTo(oval, startAngle, 60F, false)
        close()
    }

    val path1 = Path().apply {
        fillType = Path.FillType.EVEN_ODD
        moveTo(canvas.width.toFloat() / 2, canvas.height.toFloat() / 2)
        arcTo(oval1, startAngle + 60, 60F, false)
        close()
    }


    // draw path on canvas
    // it will draw triangle fill color
    canvas.drawPath(path, paint)
    paint.apply {
        color = Color.CYAN
    }
    canvas.drawPath(path1, paint)

    // change paint color to draw triangle border
    paint.apply {
        color = strokeColor
        style = Paint.Style.STROKE
    }
    // it will draw triangle border on canvas
    canvas.drawPath(path,paint)
    canvas.drawPath(path1,paint)

    return bitmap
}