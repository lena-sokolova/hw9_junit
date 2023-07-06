package officedepo.ru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "http://www.officedepo.ru/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }
}

