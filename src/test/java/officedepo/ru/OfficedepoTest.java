package officedepo.ru;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class OfficedepoTest extends TestBase {

    @DisplayName("Проверка каталога канцелярских товаров магазина Офис депо")
    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @ValueSource(strings = {"Канцелярские товары и бумага", "Школьные товары", "Товары для творчества и хобби",
            "Сопутствующие товары для офиса", "Мебель", "Бытовая и офисная техника", "Телефоны и планшеты",
            "Компьютерная техника", "Расходные материалы"})
    @ParameterizedTest(name = "Проверка наличия на сайте разделов каталога {0}")
    void pageShouldContainCatalogCategoriesNamesTest(String catalogCategoryName) {
        open(baseUrl);
        $("a[href='/catalog/']").click();
        $$(".name").find(text(catalogCategoryName)).click();
        $("#pagetitle").shouldHave(text(catalogCategoryName));
    }
}

