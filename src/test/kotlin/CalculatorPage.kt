import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory

class CalculatorPage(driver: AppiumDriver<MobileElement>) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @AndroidFindBy(id = "inputFieldLeft")
    lateinit var firstNumberInput: MobileElement

    @AndroidFindBy(id = "inputFieldRight")
    lateinit var secondNumberInput: MobileElement

    @AndroidFindBy(id = "additionButton")
    lateinit var plusButton: MobileElement

    @AndroidFindBy(id = "subtractButton")
    lateinit var minusButton: MobileElement

    @AndroidFindBy(id = "multiplicationButton")
    lateinit var multiplyButton: MobileElement

    @AndroidFindBy(id = "divisionButton")
    lateinit var divideButton: MobileElement

    @AndroidFindBy(id = "resetButton")
    lateinit var resetButton: MobileElement

    @AndroidFindBy(id = "resultTextView")
    lateinit var resultText: MobileElement

    private fun enterTextInInput(inputField: MobileElement?, text: String) {
        inputField?.click()
        inputField?.clear()
        inputField?.sendKeys(text)
    }

    fun enterFirstNumber(number: String) {
        enterTextInInput(firstNumberInput, number)
    }

    fun getFirstNumber(): String {
       return firstNumberInput.text
    }

    fun enterSecondNumber(number: String) {
        enterTextInInput(secondNumberInput, number)
    }

    fun getSecondNumber(): String {
        return secondNumberInput.text
    }

    fun getResult(): String {
        return resultText.text
    }

    fun sum(firstNumber: String, secondNumber: String): String {
        enterFirstNumber(firstNumber)
        enterSecondNumber(secondNumber)
        plusButton.click()
        return getResult()
    }

    fun subtract(firstNumber: String, secondNumber: String): String {
        enterFirstNumber(firstNumber)
        enterSecondNumber(secondNumber)
        minusButton.click()
        return getResult()
    }

    fun multiply(firstNumber: String, secondNumber: String): String {
        enterFirstNumber(firstNumber)
        enterSecondNumber(secondNumber)
        multiplyButton.click()
        return getResult()
    }

    fun divide(firstNumber: String, secondNumber: String): String {
        enterFirstNumber(firstNumber)
        enterSecondNumber(secondNumber)
        divideButton.click()
        return getResult()
    }

    fun reset() {
        resetButton.click()
    }
}