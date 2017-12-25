package net.inferno.calculator.presentor

import net.inferno.calculator.data.Calculation

class CalculatorPresenter(private val publishResult: CalculatorContract.PublishToView) : CalculatorContract.ForwardInput, CalculatorContract.ForwardInteraction, Calculation.CalculationResult {

    private val calculation = Calculation().apply {
        setCalculationResultListener(this@CalculatorPresenter)
    }

    override fun onExpressionChanged(result: String, successful: Boolean) {
        if (successful) publishResult.showResult(result)
        else publishResult.showErrorMessage(result)
    }

    override fun onNumClick(num: Char) {
        calculation.appendNumber(num)
    }

    override fun onOptClick(opt: Char) {
        if (opt == '.') calculation.appendDecimal()
        else calculation.appendOperator(opt)
    }

    override fun onEqualClick() {
        calculation.performEvaluation()
    }

    override fun onDeleteShortClick() {
        calculation.deleteCharacter()
    }

    override fun onDeleteLongClick() {
        calculation.deleteExpression()
    }
}