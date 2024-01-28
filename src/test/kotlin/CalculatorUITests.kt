import CalculatorTestData.EMPTY_STRING
import TestUtils.setupDriver
import TestUtils.tearDownDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.*

class CalculatorUITests {

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
}