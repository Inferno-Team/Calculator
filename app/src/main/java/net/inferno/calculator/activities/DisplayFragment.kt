package net.inferno.calculator.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_display.*
import net.inferno.calculator.R
import net.inferno.calculator.presentor.CalculatorContract

class DisplayFragment : Fragment(), CalculatorContract.PublishToView {

    private lateinit var forwardInteraction: CalculatorContract.ForwardInteraction

    var ForwardInteraction
        get() = forwardInteraction
        set(value) {
            forwardInteraction = value
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_display, container, true)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        optDel.setOnClickListener {
            forwardInteraction.onDeleteShortClick()
        }
        optDel.setOnLongClickListener {
            forwardInteraction.onDeleteLongClick()
            true
        }
    }

    override fun showResult(result: String) {
        if (result.isNotEmpty()) optDel.visibility = View.VISIBLE
        else optDel.visibility = View.GONE
        expression.text = result
    }

    override fun showErrorMessage(message: String) {
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}