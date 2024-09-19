package com.example.totito

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var playerOneEditText: EditText? = null
    private var playerTwoEditText: EditText? = null
    private var boardSizeRadioGroup: RadioGroup? = null
    private var startGameButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerOneEditText = findViewById<EditText>(R.id.playerOneEditText)
        playerTwoEditText = findViewById<EditText>(R.id.playerTwoEditText)
        boardSizeRadioGroup = findViewById<RadioGroup>(R.id.boardSizeRadioGroup)
        startGameButton = findViewById<Button>(R.id.startGameButton)

        startGameButton.setOnClickListener(View.OnClickListener {
            val playerOne = playerOneEditText.getText().toString()
            val playerTwo = playerTwoEditText.getText().toString()
            var boardSize = 3

            when (boardSizeRadioGroup.getCheckedRadioButtonId()) {
                R.id.radio3x3 -> boardSize = 3
                R.id.radio4x4 -> boardSize = 4
                R.id.radio5x5 -> boardSize = 5
            }
            val intent = Intent(
                this@MainActivity,
                GameActivity::class.java
            )
            intent.putExtra("playerOne", playerOne)
            intent.putExtra("playerTwo", playerTwo)
            intent.putExtra("boardSize", boardSize)
            startActivity(intent)
        })
    }
}