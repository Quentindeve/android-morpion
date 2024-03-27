package me.quentin.morpion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.core.view.iterator
import com.google.android.material.button.MaterialButton
import me.quentin.morpion.databinding.FragmentGameBinding
import me.quentin.morpion.gamelogic.Game

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    var binding: FragmentGameBinding? = null
    var game: Game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        var textView = binding!!.textView
        var replayButton = binding!!.replayButton

        var buttonX = 0
        var buttonY = 0

        replayButton.setOnClickListener {
            game = Game()

            for (widget in binding!!.grid) {
                if (widget is MaterialButton) {
                    (widget as MaterialButton).text = ""
                }
            }
            val player = game.player.toString()
            textView.text = "Au tour du $player"
            replayButton.isVisible = false
        }

        for (widget in binding!!.grid) {
            if (widget is MaterialButton) {
                val x = Integer.valueOf(buttonX)
                val y = Integer.valueOf(buttonY)

                widget.setOnClickListener {
                    Log.i("Morpion", "Clicked on $x $y")

                    val str = game.toString()
                    Log.i("Morpion", str)
                    val character = game.play(x, y)

                    if (character != null) {
                        (it!! as MaterialButton).text = character
                    } else {
                        // TODO
                    }

                    val gameState = game.gameState()
                    Log.i("Morpion", "Game State: $gameState")

                    when (gameState) {
                        Game.GameState.Player1Won -> {
                            textView.text = "Le joueur 1 a gagné !"
                            game.lock()
                            replayButton.isVisible = true
                        }

                        Game.GameState.Player2Won -> {
                            textView.text = "Le joueur 2 a gagné !"
                            game.lock()
                            replayButton.isVisible = true
                        }

                        Game.GameState.Draw -> {
                            textView.text = "Egalité !"
                            game.lock()
                            replayButton.isVisible = true
                        }

                        null -> {
                            val player = game.player.toString()
                            textView.text = "Au tour du $player"
                        }
                    }
                }

                buttonX += 1
                if (buttonX > 2) {
                    buttonX = 0
                    buttonY += 1
                }
            }
        }

        return binding!!.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         **/
        @JvmStatic
        fun newInstance() =
            GameFragment().apply {}
    }
}