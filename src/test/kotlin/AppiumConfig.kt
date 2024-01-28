import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

object AppiumConfig {

    const val URL = "http://127.0.0.1:4723"

    private const val APP_PATH = "/Users/admin/Downloads/Telegram Desktop/app-debug.apk"
    private const val PLATFORM_NAME = "Android"
    private const val DEVICE_NAME = "emulator-5554"
    private const val AUTOMATION_NAME = "UiAutomator2"
    private const val LANGUAGE = "ru"
    private const val LOCALE = "RU"

    fun createCapabilities(): DesiredCapabilities {
        return DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME)
            setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME)
            setCapability(MobileCapabilityType.APP, APP_PATH)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME)
            setCapability(MobileCapabilityType.LANGUAGE, LANGUAGE)
            setCapability(MobileCapabilityType.LOCALE, LOCALE)
        }
    }
}