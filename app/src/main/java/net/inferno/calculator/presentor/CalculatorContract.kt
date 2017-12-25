package net.inferno.calculator.presentor

interface CalculatorContract {
    interface PublishToView {
        fun showResult(result: String)
        fun showErrorMessage(message: String)
    }

    interface ForwardInteraction {
        fun onDeleteShortClick()
        fun onDeleteLongClick()
    }

    interface ForwardInput {
        fun onNumClick(num: Char)
        fun onOptClick(opt: Char)
        fun onEqualClick()
    }
}