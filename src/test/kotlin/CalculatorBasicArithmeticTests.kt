import TestUtils.performArithmeticTest
import TestUtils.setupDriver
import TestUtils.tearDownDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CalculatorBasicArithmeticTests {

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
    @DisplayName("Тестирование сложения с обычными числами")
    @MethodSource("CalculatorTestData#provideBasicSumTestCases")
    fun testBasicSumCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.ADDITION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование вычитания с обычными числами")
    @MethodSource("CalculatorTestData#provideBasicSubtractTestCases")
    fun testBasicSubtractCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.SUBTRACTION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование умножения с обычными числами")
    @MethodSource("CalculatorTestData#provideBasicMultiplyTestCases")
    fun testBasicMultiplyCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.MULTIPLICATION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }

    @ParameterizedTest
    @DisplayName("Тестирование деления с обычными числами")
    @MethodSource("CalculatorTestData#provideBasicDivideTestCases")
    fun testBasicDivideCases(firstNumber: String, secondNumber: String, expectedResult: String) {
        performArithmeticTest(
            calculatorPage = calculatorPage,
            operation = ArithmeticOperation.DIVISION,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            expectedResult = expectedResult
        )
    }
}