package com.irempamukcu.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.irempamukcu.tictactoe.databinding.ActivityPlayingBinding

class Playing : AppCompatActivity() {
    private var playTime = 0
    private lateinit var winnerImage : ImageView
    lateinit var mediaPlayer : MediaPlayer

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private lateinit var binding: ActivityPlayingBinding

    private var currentTurn = Turn.CROSS
    private var boardList = mutableListOf<ImageView>()
    private val board = Array(3) { arrayOfNulls<Turn>(3) }
    private var first = ""
    private var second = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        first = intent.getStringExtra("first")!!
        second = intent.getStringExtra("second")!!


        initBoard()
        mediaPlayer = MediaPlayer.create(this,R.raw.tap)

    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)

        for (image in boardList) {
            image.setOnClickListener { boardTapped(it) }
        }
    }

    fun boardTapped(view: View) {
        if (view !is ImageView) {
            return
        }

        if (view.drawable != null) {
            return
        }

        mediaPlayer.start()

        addToBoard(view)

        if (checkForVictory()) {
            result("${winner()} Kazandı!")
        } else if (fullBoard()) {
            result("Berabere!")
        }

        playTime++
        currentTurn = if (currentTurn == Turn.CROSS) Turn.NOUGHT else Turn.CROSS
        setTurnLabel()


    }

    private fun addToBoard(image: ImageView) {
        val row = boardList.indexOf(image) / 3
        val col = boardList.indexOf(image) % 3

        if (currentTurn == Turn.CROSS) {
            if(first == "frog"){
                image.setImageResource(R.drawable.frog)
                board[row][col] = Turn.CROSS
            }else if(first == "jellyfish"){
                image.setImageResource(R.drawable.jellyfish)
                board[row][col] = Turn.CROSS
            }

        } else {
            if(second == "snake"){
                image.setImageResource(R.drawable.snake)
                board[row][col] = Turn.NOUGHT
            }else if(second == "snail"){
                image.setImageResource(R.drawable.snail)
                board[row][col] = Turn.NOUGHT
            }

        }
    }

    private fun checkForVictory(): Boolean {

        for (i in 0..2) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true
            }
        }

        // Check columns
        for (i in 0..2) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true
            }
        }

        // Check diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true
        }

        return false
    }

    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("Tekrar Oyna") { _, _ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finishAffinity()
    }

    private fun fullBoard(): Boolean {
        return playTime >= 8
    }

    private fun setTurnLabel() {
        if (currentTurn == Turn.CROSS) {
            if (first == "frog") {
                binding.miniImage.setImageResource(R.drawable.frog)
            } else if (first == "jellyfish") {
                binding.miniImage.setImageResource(R.drawable.jellyfish)
            }
        } else if (currentTurn == Turn.NOUGHT) {
            if (second == "snake") {
                binding.miniImage.setImageResource(R.drawable.snake)
            } else if (second == "snail") {
                binding.miniImage.setImageResource(R.drawable.snail)
            }
        }
    }

    private fun winner(): String{
        if(currentTurn == Turn.CROSS){
            if(first == "frog"){
                return "Kurbağa"
            }else{
                return "Denizanası"
            }
        }else{
            if(second == "snake"){
                return "Yılan"
            }else{
                return "Salyangoz"
            }
        }
    }

}
