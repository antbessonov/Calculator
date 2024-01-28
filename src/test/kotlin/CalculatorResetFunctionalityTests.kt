import CalculatorTestData.EMPTY_STRING
import TestUtils.setupDriver
import TestUtils.tearDownDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CalculatorResetFunctionalityTests {

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
}