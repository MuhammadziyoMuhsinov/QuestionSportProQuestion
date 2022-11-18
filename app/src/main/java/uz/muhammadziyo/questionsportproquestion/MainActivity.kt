package uz.muhammadziyo.questionsportproquestion

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import uz.muhammadziyo.questionsportproquestion.databinding.ActivityMainBinding
import uz.muhammadziyo.questionsportproquestion.databinding.ItemDialogBinding
import uz.muhammadziyo.questionsportproquestion.models.MyData
import uz.muhammadziyo.questionsportproquestion.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<Question>
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(object : Runnable{
            override fun run() {
                binding.splash.visibility  = View.GONE
            }
        },3000)

        loadList()
        loadQuestion()

        binding.containerQuestion1.setOnClickListener {
            chekQuestion(1)
        }
        binding.containerQuestion2.setOnClickListener {
            chekQuestion(2)
        }
        binding.containerQuestion3.setOnClickListener {
            chekQuestion(3)
        }

    }

    fun loadList() {
        list = ArrayList()

        list.add(Question("The Olympics are held every how many years?",
            "2",
            "3",
            "4",
            3))

        list.add(Question("What’s the diameter of a basketball hoop in inches?",
            "19",
            "18",
            "17",
            2
        ))

        list.add(Question("How many dimples does an average golf ball have?",
            "336",
            "350",
            "400",
            1
        ))

        list.add(Question("How many medals did China win at the Beijing Olympics?",
            "300",
            "200",
            "100",
            3
        ))

        list.add(Question("How many holes are played in an average round of golf?",
            "15",
            "8",
            "18",
            3
        ))

        list.add(Question("In football, how many points does a touchdown hold?",
            "6",
            "7",
            "8",
            1
        ))

        list.add(Question("How many players are on a baseball team?",
            "9",
            "8",
            "6",
            1
        ))

        list.add(Question("How many sports were included in the 2008 Summer Olympics?",
            "30",
            "29",
            "28",
            3
        ))

        list.add(Question("How old was Tiger Woods when he won the Masters?",
        "19",
            "20",
            "21",
            3
            ))

        list.add(Question("How many minutes was the longest recorded point in the history of tennis?",
        "30",
            "29",
            "28",
            2
            ))

    }

    private fun loadQuestion() {
        binding.txtQuestion.text = list[position].question
        binding.answer1.text = list[position].answer1
        binding.answer2.text = list[position].answer2
        binding.answer3.text = list[position].answer3

    }

    private fun alertDialog() {

        val alertDialog = AlertDialog.Builder(binding.root.context,R.style.AlertDialog).create()
        val itemDialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(binding.root.context))
        alertDialog.setView(itemDialogBinding.root)
        itemDialogBinding.txtBall.text = "${MyData.ball} BALLS"
        binding.level.text = "1"
        binding.ball.text = "0"
        MyData.ball = 0
        MyData.level = 0
        position = 0
        binding.xira.visibility = View.VISIBLE
        alertDialog.show()
        alertDialog.setOnDismissListener {
            binding.xira.visibility = View.INVISIBLE
        }

    }

    private fun chekQuestion(answer: Int) {
        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                MyData.correct += 1
                MyData.ball += 50

                alertDialog()
            } else {
                position += 1
                MyData.correct += 1
                MyData.ball += 50
                MyData.level += 1
                binding.ball.text = MyData.ball.toString()
                binding.level.text = MyData.level.toString()
                Snackbar.make(binding.root, "Correct!", 1000).show()
                loadQuestion()
            }

        } else {
            if (list.size == position + 1) {
                MyData.incorrect += 1
                alertDialog()
            } else {
                MyData.incorrect += 1
                MyData.level += 1
                binding.level.text = MyData.level.toString()
                Snackbar.make(binding.root, "Wrong!", 1000).show()
                position += 1
                loadQuestion()

            }

        }
    }

}