package net.inferno.calculator.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.inferno.calculator.R
import net.inferno.calculator.presentor.CalculatorPresenter

class MainActivity : AppCompatActivity() {

    val display by lazy { displayFragment as DisplayFragment }
    val input by lazy { inputFragment as InputFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = CalculatorPresenter(display)
        display.ForwardInteraction = presenter
        input.ForwardInput = presenter
    }
}
