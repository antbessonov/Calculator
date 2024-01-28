import TestUtils.setupDriver
import TestUtils.tearDownDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.ScreenOrientation
import java.time.Duration

class CalculatorStatePersistenceTests {

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