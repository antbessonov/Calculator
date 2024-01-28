import org.junit.jupiter.api.Assertions

object TestUtils {

    fun performArithmeticTest(
        calculatorPage: CalculatorPage,
        operation: ArithmeticOperation,
        firstNumber: String,
        secondNumber: String,
        expectedResult: String
    ) {
        val actualResult = when (operation) {
            ArithmeticOperation.ADDITION -> calculatorPage.sum(firstNumber, secondNumber)
            ArithmeticOperation.SUBTRACTION -> calculatorPage.subtract(firstNumber, secondNumber)
            ArithmeticOperation.MULTIPLICATION -> calculatorPage.multiply(firstNumber, secondNumber)
            ArithmeticOperation.DIVISION -> calculatorPage.divide(firstNumber, secondNumber)
        }

        val errorMessage = createErrorMessageForArithmeticTest(
            operation = operation,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult,
            actualResult = actualResult
        )

        Assertions.assertEquals(expectedResult, actualResult, errorMessage)
    }

    private fun createErrorMessageForArithmeticTest(
        operation: ArithmeticOperation,
        firstNumber: String,
        secondNumber: String,
        expectedResult: String,
        actualResult: String
    ): String {
        val operationStr = when(operation) {
            ArithmeticOperation.ADDITION -> "сложении"
            ArithmeticOperation.SUBTRACTION -> "вычитании"
            ArithmeticOperation.MULTIPLICATION -> "умножении"
            ArithmeticOperation.DIVISION -> "делении"
        }
        return "Ошибка при $operationStr чисел $firstNumber и $secondNumber. " +
                "Ожидаемый результат: $expectedResult, полученный результат: $actualResult"
    }
}