package net.inferno.calculator.data

import org.javia.arity.Symbols
import org.javia.arity.SyntaxException
import java.text.NumberFormat

class Calculation {

    private val symbols: Symbols = Symbols()
    private lateinit var calculationResult: CalculationResult
    private var currentExpression = ""
    private var displayExpression = ""
        get() {
            var tempExpression = currentExpression.replace('/', '\u00F7')
            tempExpression = tempExpression.replace('*', '\u00D7')
            //tempExpression = tempExpression.replace('1', '\u00FD')
            return tempExpression
        }

    private var lastOperator = '\n'
    private var isDecimal = false

    interface CalculationResult {
        fun onExpressionChanged(result: String, successful: Boolean)
    }

    fun setCalculationResultListener(calculationResult: CalculationResult) {
        this.calculationResult = calculationResult
    }

    fun deleteCharacter() {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length - 1)
            calculationResult.onExpressionChanged(displayExpression, true)
        }
    }

    fun deleteExpression() {
        if (currentExpression == "") {
            calculationResult.onExpressionChanged("Invalid Input", false)
        }
        currentExpression = ""
        calculationResult.onExpressionChanged(displayExpression, true)
    }

    fun appendNumber(number: Char) {
        if (currentExpression.length <= 24) {
            currentExpression += number
            lastOperator = '\n'
            calculationResult.onExpressionChanged(displayExpression, true)
        }
    }

    fun appendOperator(operator: Char) {
        if (validateExpression(operator)) {
            currentExpression += operator
            lastOperator = operator
            isDecimal = false
            calculationResult.onExpressionChanged(displayExpression, true)
        } else if (operator in "+*/") {
            currentExpression = currentExpression.substring(0, currentExpression.length - 1)
            currentExpression += operator
            calculationResult.onExpressionChanged(displayExpression, true)
        }
    }

    fun appendDecimal() {
        if (validateExpression('.')) {
            currentExpression += "."
            lastOperator = '.'
            isDecimal = true
            calculationResult.onExpressionChanged(displayExpression, true)
        }
    }

    fun performEvaluation() {
        try {
            val result = symbols.eval(currentExpression)
            currentExpression = NumberFormat.getInstance()
                .format(result)
            calculationResult.onExpressionChanged(displayExpression, true)
        } catch (e: SyntaxException) {
            calculationResult.onExpressionChanged("Invalid Input", false)
            e.printStackTrace()
        }
    }

    private fun validateExpression(operator: Char) =
        if ((lastOperator == '*' || lastOperator == '/' || lastOperator == '+') && operator != '.' && operator != '-') false
        else if (operator == '-' && lastOperator == '-') false
        else !(operator == '.' && isDecimal)
}