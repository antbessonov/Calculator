import CalculatorTestData.EMPTY_STRING
import TestUtils.performArithmeticTest
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.ScreenOrientation
import java.net.URL
import java.time.Duration

class CalculatorTests {

    private lateinit var calculatorPage: CalculatorPage

    companion object {

        private lateinit var driver: AppiumDriver<MobileElement>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val url = URL(AppiumConfig.URL)
            val caps = AppiumConfig.createCapabilities()
            driver = AppiumDriver(url, caps)
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            driver.quit()
        }
    }

    @BeforeEach
    fun prepare() {
        calculatorPage = CalculatorPage(driver)
    }

    @Test
    @DisplayName("Проверка наличия основных элементов пользовательского интерфейса")
    fun testPresenceOfUIElements() {
        assertAll(
            "Проверка наличия всех элементов UI",
            { Assertions.assertTrue(calculatorPage.firstNumberInput.isDisplayed, "Первое поле ввода отсутствует") },
            { Assertions.assertTrue(calculatorPage.secondNumberInput.isDisplayed, "Второе поле ввода отсутствует") },
            { Assertions.assertTrue(calculatorPage.plusButton.isDisplayed, "Кнопка сложения отсутствует") },
            { Assertions.assertTrue(calculatorPage.minusButton.isDisplayed, "Кнопка вычитания отсутствует") },
            { Assertions.assertTrue(calculatorPage.multiplyButton.isDisplayed, "Кнопка умножения отсутствует") },
            { Assertions.assertTrue(calculatorPage.divideButton.isDisplayed, "Кнопка деления отсутствует") },
            { Assertions.assertTrue(calculatorPage.resetButton.isDisplayed, "Кнопка сброса отсутствует") },
            { Assertions.assertTrue(calculatorPage.resultText.isDisplayed, "Поле для вывода результата отсутствует") },
        )
    }

    @Test
    @DisplayName("Проверка начальных значений элементов интерфейса")
    fun testInitialValuesOfUIElements() {
        assertAll("Проверка начальных значений UI",
            {
                Assertions.assertEquals(
                    EMPTY_STRING,
                    calculatorPage.firstNumberInput.text,
                    "Неверное начальное значение первого поля ввода"
                )
            },
            {
                Assertions.assertEquals(
                    EMPTY_STRING,
                    calculatorPage.secondNumberInput.text,
                    "Неверное начальное значение второго поля ввода"
                )
            },
            {
                Assertions.assertEquals(
                    EMPTY_STRING,
                    calculatorPage.resultText.text,
                    "Неверное начальное значение поля результата"
                )
            }
        )
    }

    @Tag("basic")
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

    @Tag("basic")
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

    @Tag("basic")
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

    @Tag("basic")
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

    @ParameterizedTest
    @DisplayName("Тест сброса с различными входными данными")
    @MethodSource("CalculatorTestData#provideInputForResetFunctionalityTest")
    fun testResetFunctionalityWithVariousInputs(firstNumber: String, secondNumber: String, expectedOutput: String) {
        calculatorPage.apply {
            enterFirstNumber(firstNumber)
            enterSecondNumber(secondNumber)
            reset()
        }
        assertAll("Проверка очистки всех полей ввода",
            {
                Assertions.assertEquals(
                    expectedOutput,
                    calculatorPage.getFirstNumber(),
                    "Первое поле ввода не очищено"
                )
            },
            {
                Assertions.assertEquals(
                    expectedOutput,
                    calculatorPage.getSecondNumber(),
                    "Второе поле ввода не очищено"
                )
            }
        )
    }

    @Test
    @DisplayName("Тест сброса без входных данных")
    fun testResetWithoutInput() {
        calculatorPage.reset()
        assertAll("Проверка очистки всех полей ввода",
            {
                Assertions.assertEquals(
                    EMPTY_STRING,
                    calculatorPage.getFirstNumber(),
                    "Первое поле ввода не очищено"
                )
            },
            {
                Assertions.assertEquals(
                    EMPTY_STRING,
                    calculatorPage.getSecondNumber(),
                    "Второе поле ввода не очищено"
                )
            }
        )
    }

    @ParameterizedTest
    @DisplayName("Проверка сохранения введенных чисел в полях ввода после переворота экрана")
    @MethodSource("CalculatorTestData#provideTestDataForMinimizeAndRestoreTest")
    fun testInputPersistenceAfterScreenRotation(firstNumber: String, secondNumber: String) {
        val originalOrientation = driver.orientation
        try {
            calculatorPage.enterFirstNumber(firstNumber)
            calculatorPage.enterSecondNumber(secondNumber)
            driver.rotate(ScreenOrientation.LANDSCAPE)
            val firstNumberAfterRotation = calculatorPage.getFirstNumber()
            val secondNumberAfterRotation = calculatorPage.getSecondNumber()
            assertAll("Проверка сохранения ввода после переворота экрана",
                {
                    Assertions.assertEquals(
                        firstNumber,
                        firstNumberAfterRotation,
                        "Первое число изменилось после переворота экрана"
                    )
                },
                {
                    Assertions.assertEquals(
                        secondNumber,
                        secondNumberAfterRotation,
                        "Второе число изменилось после переворота экрана"
                    )
                }
            )
        } finally {
            driver.rotate(originalOrientation)
        }
    }


    @ParameterizedTest
    @DisplayName("Тестирование сохранения результата после переворота экрана")
    @MethodSource("CalculatorTestData#provideTestDataForMinimizeAndRestoreTest")
    fun testResultPersistsAfterScreenRotation(firstNumber: String, secondNumber: String) {
        val originalOrientation = driver.orientation
        try {
            calculatorPage.sum(firstNumber, secondNumber)
            val resultBeforeRotation = calculatorPage.getResult()
            driver.rotate(ScreenOrientation.LANDSCAPE)
            val resultAfterRotation = calculatorPage.getResult()
            Assertions.assertEquals(
                resultBeforeRotation,
                resultAfterRotation,
                "Результаты вычислений отличаются после переворота экрана"
            )
        } finally {
            driver.rotate(originalOrientation)
        }
    }

    @ParameterizedTest
    @DisplayName("Тестирование сохранения результата после сворачивания и восстановления приложения")
    @MethodSource("CalculatorTestData#provideTestDataForMinimizeAndRestoreTest")
    fun testResultPersistsAfterMinimizeAndRestoreApp(firstNumber: String, secondNumber: String) {
        calculatorPage.sum(firstNumber, secondNumber)
        val resultBeforeMinimize = calculatorPage.getResult()
        driver.runAppInBackground(Duration.ofSeconds(1))
        val resultAfterRestore = calculatorPage.getResult()
        Assertions.assertEquals(resultBeforeMinimize, resultAfterRestore)
    }
}