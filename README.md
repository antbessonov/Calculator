# Тестирование мобильного приложения калькулятора

Набор автотестов для тестирования мобильного приложения калькулятора и багрепорты.

>Актуальный код находиться в ветке `develop`

Стек: Kotlin, Appium, JUnit5

## Автоматизация

Тесты охватывают основные арифметические операции, включая сложение, вычитание, умножение и деление, а также тестирование функциональности сброса и сохранения состояния при изменении ориентации экрана и сворачивании приложения.

### Общая структура

#### Класс AppiumConfig

`AppiumConfig` содержит конфигурацию для подключения к Appium серверу и создания capabilities для драйвера Appium.

Основные компоненты:
- `URL`: URL Appium сервера.
- `APP_PATH`: Путь к APK приложения для тестирования.
- `PLATFORM_NAME`: Платформа устройства (например, Android).
- `DEVICE_NAME`: Название устройства для тестирования.
- `AUTOMATION_NAME`: Используемый фреймворк автоматизации (например, UiAutomator2 для Android).

`createCapabilities()`: Создаёт объект DesiredCapabilities, который содержит набор параметров для Appium драйвера.

#### Класс CalculatorPage

`CalculatorPage` описывает элементы интерфейса приложения калькулятора и основные методы взаимодействия с ними.

Основные компоненты:
- `firstNumberInput`, `secondNumberInput`, `plusButton` и др.: Объекты `MobileElement`, представляющие элементы интерфейса калькулятора.
- `enterFirstNumber()`, `enterSecondNumber()`: Методы для ввода чисел.
- `sum()`, `subtract()`, `multiply()`, `divide()`: Методы для выполнения арифметических операций.
- `reset()`: Метод для сброса введённых данных.

#### Enum ArithmeticOperation

`ArithmeticOperation` представляет собой перечисление, которое содержит типы арифметических операций (сложение, вычитание, умножение, деление).

#### Класс TestUtils

`TestUtils` содержит вспомогательные методы для выполнения и валидации арифметических тестов.

Основные методы:
- `performArithmeticTest()`: Метод для выполнения арифметического теста и проверки результата.
- `createErrorMessageForArithmeticTest()`: Генерирует сообщение об ошибке для арифметических тестов.

#### Класс CalculatorTests

`CalculatorTests` содержит тестовые методы для проверки функциональности мобильного приложения калькулятора.

Основные методы:
- Тестирование базовых арифметических операций. Тесты, помеченные тегом `@Tag("basic")`, фокусируются на основных арифметических операциях (сложение, вычитание, умножение, деление) с базовыми проверками.
Пример: `testBasicSumCases()`, `testBasicSubtractCases()`, `testBasicMultiplyCases()`, `testBasicDivideCases()`.
- Тестирование с граничными значениями. Проверяют поведение приложения на границах допустимых значений. Примеры: `testBoundarySumCases()`, `testBoundarySubtractCases()`, `testBoundaryMultiplyCases()`, `testBoundaryDivideCases()`.
- Тестирование с некорректными входными данными. Проверяют, как приложение реагирует на неправильный или невалидный ввод. Примеры: `testNegativeSumTestCases()`, `testNegativeSubtractCases()`, `testNegativeMultiplyCases()`, `testNegativeDivideCases()`.
- Тестирование в специальных условиях. Проверяют поведение приложения в специфических ситуациях, например, с дробями или отрицательными числами. Примеры: `testSpecialSumTestCases()`, `testSpecialSubtractTestCases()`, `testSpecialMultiplyTestCases()`, `testSpecialDivideTestCases()`.
- Тестирование функционала сброса. Проверяют работу функционала сброса в приложении. Примеры: `testResetFunctionalityWithVariousInputs()`, `testResetWithoutInput()`.
- Тестирование сохранения состояния. Проверяют, сохраняется ли состояние и результаты приложения при различных изменениях, например, при перевороте экрана или сворачивании приложения. Примеры: `testResultPersistsAfterScreenRotation()`, `testResultPersistsAfterMinimizeAndRestoreApp()`.

Методы, помеченные `@MethodSource`, предоставляют параметризованные данные для тестов.

#### Класс CalculatorTestData

`CalculatorTestData` предоставляет тестовые данные для параметризованных тестов.

`provideBasicSumTestCases()`, `provideSpecialSumTestCases()` и др.: Методы, возвращающие наборы данных для различных типов арифметических тестов.

### Запуск тестов

Краткая инструкция по запуску тестового набора для мобильного приложения калькулятора включает следующие шаги.

#### Предварительные требования

- Установка и настройка Appium. Убедитесь, что Appium установлен и настроен на вашем компьютере. Это включает в себя установку Node.js и самого Appium.
- Настройка Android SDK/Emulator. Для тестирования мобильного приложения на Android, необходимо иметь установленный и настроенный Android SDK с эмулятором.
- Установка Java и Kotlin. Убедитесь, что у вас установлены Java и Kotlin, так как тесты написаны на Kotlin.
- IDE для Разработки. Рекомендуется использовать IntelliJ IDEA для открытия и запуска проекта.

#### Шаги для запуска тестов

- Запустите Appium Server.
- Откройте ваш проект в IntelliJ.
- Запустите Android Emulator, на котором будет выполняться тестирование.
- Перейдите к `AppiumConfig`, который содержит конфигурацию для Appium. Убедитесь, что URL и capabilities для Appium Server настроены правильно.
- В вашей IDE, найдите файл `CalculatorTests`. Щелкните правой кнопкой мыши по файлу и выберите Run CalculatorTests для запуска всех тестов. Для запуска отдельного теста щелкните правой кнопкой мыши по названию теста и выберите Run.
- После выполнения тестов проверьте результаты в окне вывода вашей IDE.
