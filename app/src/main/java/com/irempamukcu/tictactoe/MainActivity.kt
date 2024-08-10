package com.irempamukcu.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.irempamukcu.tictactoe.databinding.ActivityMainBinding
import com.irempamukcu.tictactoe.databinding.ActivityPlayingBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var first = ""
    private var second = ""
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this,R.raw.tap)

        visibility(binding.root)
        setVisibility(binding.root)

        binding.start.setOnClickListener {
            if(first != "" && second != ""){
                nextActivity(binding.root)
            }

        }


    }

    private fun nextActivity(view: View) {
        val intent = Intent(this,Playing::class.java)
        intent.putExtra("first",first)
        intent.putExtra("second",second)
        startActivity(intent)

    }

    private fun visibility(view : View){
        binding.frogrow1.visibility = View.INVISIBLE
        binding.frogrow2.visibility = View.INVISIBLE
        binding.frogrow3.visibility = View.INVISIBLE
        binding.frogrow4.visibility = View.INVISIBLE
        binding.jellyrow1.visibility = View.INVISIBLE
        binding.jellyrow2.visibility = View.INVISIBLE
        binding.jellyrow3.visibility = View.INVISIBLE
        binding.jellyrow4.visibility = View.INVISIBLE
        binding.snakerow1.visibility = View.INVISIBLE
        binding.snakerow2.visibility = View.INVISIBLE
        binding.snakerow3.visibility = View.INVISIBLE
        binding.snakerow4.visibility = View.INVISIBLE
        binding.snailrow1.visibility = View.INVISIBLE
        binding.snailrow2.visibility = View.INVISIBLE
        binding.snailrow3.visibility = View.INVISIBLE
        binding.snailrow4.visibility = View.INVISIBLE


    }

    private fun setVisibility(view : View){
        binding.frog.setOnClickListener {
            mediaPlayer.start()
            binding.frogrow1.visibility = View.VISIBLE
            binding.frogrow2.visibility = View.VISIBLE
            binding.frogrow3.visibility = View.VISIBLE
            binding.frogrow4.visibility = View.VISIBLE
            binding.jellyrow1.visibility = View.INVISIBLE
            binding.jellyrow2.visibility = View.INVISIBLE
            binding.jellyrow3.visibility = View.INVISIBLE
            binding.jellyrow4.visibility = View.INVISIBLE
            first = "frog"

        }

        binding.jellyfish.setOnClickListener {
            mediaPlayer.start()
            binding.frogrow1.visibility = View.INVISIBLE
            binding.frogrow2.visibility = View.INVISIBLE
            binding.frogrow3.visibility = View.INVISIBLE
            binding.frogrow4.visibility = View.INVISIBLE
            binding.jellyrow1.visibility = View.VISIBLE
            binding.jellyrow2.visibility = View.VISIBLE
            binding.jellyrow3.visibility = View.VISIBLE
            binding.jellyrow4.visibility = View.VISIBLE
            first = "jellyfish"
        }



        binding.snake.setOnClickListener {
            mediaPlayer.start()
            binding.snakerow1.visibility = View.VISIBLE
            binding.snakerow2.visibility = View.VISIBLE
            binding.snakerow3.visibility = View.VISIBLE
            binding.snakerow4.visibility = View.VISIBLE
            binding.snailrow1.visibility = View.INVISIBLE
            binding.snailrow2.visibility = View.INVISIBLE
            binding.snailrow3.visibility = View.INVISIBLE
            binding.snailrow4.visibility = View.INVISIBLE
            second = "snake"


        }

        binding.snail.setOnClickListener {
            mediaPlayer.start()
            binding.snakerow1.visibility = View.INVISIBLE
            binding.snakerow2.visibility = View.INVISIBLE
            binding.snakerow3.visibility = View.INVISIBLE
            binding.snakerow4.visibility = View.INVISIBLE
            binding.snailrow1.visibility = View.VISIBLE
            binding.snailrow2.visibility = View.VISIBLE
            binding.snailrow3.visibility = View.VISIBLE
            binding.snailrow4.visibility = View.VISIBLE

            second = "snail"
        }


    }
}