package com.example.totito

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class GameActivity : AppCompatActivity() {
    private var playerOne: String? = null
    private var playerTwo: String? = null
    private var boardSize = 0
    private var isPlayerOneTurn = false
    private var currentPlayerTextView: TextView? = null
    private var gameBoard: GridLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        playerOne = intent.getStringExtra("playerOne")
        playerTwo = intent.getStringExtra("playerTwo")
        boardSize = intent.getIntExtra("boardSize", 3)

        currentPlayerTextView = findViewById<TextView>(R.id.currentPlayerTextView)
        gameBoard = findViewById<GridLayout>(R.id.gameBoard)

        setupGameBoard()

        // Selección aleatoria de quién inicia
        isPlayerOneTurn = Random().nextBoolean()
        updateCurrentPlayerText()
    }

    private fun setupGameBoard() {
        gameBoard!!.columnCount = boardSize
        gameBoard!!.rowCount = boardSize

        for (i in 0 until boardSize * boardSize) {
            val cell = Button(this)
            cell.layoutParams = GridLayout.LayoutParams()
            cell.setOnClickListener { v: View? ->
                onCellClicked(
                    cell
                )
            }
            gameBoard!!.addView(cell)
        }
    }

    private fun onCellClicked(cell: Button) {
        if (!cell.text.toString().isEmpty()) {
            Toast.makeText(this, "Celda ocupada", Toast.LENGTH_SHORT).show()
            return
        }

        if (isPlayerOneTurn) {
            cell.text = "X"
        } else {
            cell.text = "O"
        }

        if (checkWinner()) {
            val winner = if (isPlayerOneTurn) playerOne else playerTwo
            Toast.makeText(this, "$winner ha ganado!", Toast.LENGTH_LONG).show()
            finish()
        } else {
            isPlayerOneTurn = !isPlayerOneTurn
            updateCurrentPlayerText()
        }
    }

    private fun updateCurrentPlayerText() {
        val currentPlayer = if (isPlayerOneTurn) playerOne else playerTwo
        currentPlayerTextView!!.text = "Turno de: $currentPlayer"
    }

    private fun checkWinner(): Boolean {
        // Lógica para comprobar si alguien ganó (tres en fila)
        // Por simplicidad, solo la lógica para 3x3, puedes expandir esto para 4x4 o 5x5.
        // Deberías revisar filas, columnas y diagonales.
        return false // Sustituir con lógica real
    }
}