package net.inferno.calculator.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_input.*
import net.inferno.calculator.R
import net.inferno.calculator.presentor.CalculatorContract

class InputFragment : Fragment() {

    private val numClickListener = View.OnClickListener {
        forwardInput.onNumClick(it.tag.toString()[0])
    }

    private val optClickListener = View.OnClickListener {
        forwardInput.onOptClick(it.tag.toString()[0])
    }

    private lateinit var forwardInput: CalculatorContract.ForwardInput

    var ForwardInput
        get() = forwardInput
        set(value) {
            forwardInput = value
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_input, container, true)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        num0.setOnClickListener(numClickListener)
        num1.setOnClickListener(numClickListener)
        num2.setOnClickListener(numClickListener)
        num3.setOnClickListener(numClickListener)
        num4.setOnClickListener(numClickListener)
        num5.setOnClickListener(numClickListener)
        num6.setOnClickListener(numClickListener)
        num7.setOnClickListener(numClickListener)
        num8.setOnClickListener(numClickListener)
        num9.setOnClickListener(numClickListener)

        optAdd.setOnClickListener(optClickListener)
        optSub.setOnClickListener(optClickListener)
        optDiv.setOnClickListener(optClickListener)
        optMul.setOnClickListener(optClickListener)
        optDot.setOnClickListener(optClickListener)

        optEqual.setOnClickListener {
            forwardInput.onEqualClick()
        }
    }
}