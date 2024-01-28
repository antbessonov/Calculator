import TestUtils.performArithmeticTest
import TestUtils.setupDriver
import TestUtils.tearDownDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CalculatorNegativeArithmeticTests {

    private lateinit var calculatorPage: CalculatorPage

    companion object {

        private lateinit var driver: AppiumDriver<MobileElement>

        @BeforeAll
        @JvmStatic
        fun setup() {
            driver = setupDriver()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            tearDownDriver(driver)
        }
    }

    @BeforeEach
    fun prepare() {
        calculatorPage = CalculatorPage(driver)
    }

    @ParameterizedTest
    @DisplayName("Тестирование сложения с граничными значениями")
    @MethodSource("CalculatorTestData#testSumBoundaryCases")
    fun testBoundarySumCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.ADDITION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование сложения с некорректными входными данными")
    @MethodSource("CalculatorTestData#provideNegativeTestCases")
    fun testNegativeSumTestCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.ADDITION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование сложения в специальных условиях (дроби, отрицательные числа)")
    @MethodSource("CalculatorTestData#provideSpecialSumTestCases")
    fun testSpecialSumTestCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.ADDITION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование вычитания с граничными значениями")
    @MethodSource("CalculatorTestData#provideSubtractBoundaryCases")
    fun testBoundarySubtractCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.SUBTRACTION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование вычитания с некорректными входными данными")
    @MethodSource("CalculatorTestData#provideNegativeTestCases")
    fun testNegativeSubtractCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.SUBTRACTION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование вычитания в специальных условиях (дроби, отрицательные числа)")
    @MethodSource("CalculatorTestData#provideSpecialSubtractTestCases")
    fun testSpecialSubtractTestCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.SUBTRACTION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование умножения с граничными значениями")
    @MethodSource("CalculatorTestData#provideMultiplyBoundaryCases")
    fun testBoundaryMultiplyCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.MULTIPLICATION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование умножения с некорректными входными данными")
    @MethodSource("CalculatorTestData#provideNegativeTestCases")
    fun testNegativeMultiplyCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.MULTIPLICATION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование умножения в специальных условиях (дроби, отрицательные числа)")
    @MethodSource("CalculatorTestData#provideSpecialMultiplyTestCases")
    fun testSpecialMultiplyTestCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.MULTIPLICATION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование деления с граничными значениями")
    @MethodSource("CalculatorTestData#provideDivideBoundaryCases")
    fun testBoundaryDivideCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.DIVISION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование деления с некорректными входными данными")
    @MethodSource("CalculatorTestData#provideNegativeTestCases")
    fun testNegativeDivideCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.DIVISION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование деления в специальных условиях (дроби, отрицательные числа)")
    @MethodSource("CalculatorTestData#provideSpecialDivideTestCases")
    fun testSpecialDivideTestCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.DIVISION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }
}