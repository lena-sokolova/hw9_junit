package officedepo.ru;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class OfficedepoTest extends TestBase {

    @ValueSource(strings = {"Канцелярские товары и бумага", "Школьные товары", "Товары для творчества и хобби",
            "Сопутствующие товары для офиса", "Мебель", "Бытовая и офисная техника", "Телефоны и планшеты",
            "Компьютерная техника", "Расходные материалы"})

    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })

    @DisplayName("Проверка каталога канцелярских товаров магазина Офис депо")
    @ParameterizedTest(name = "Проверка наличия на сайте разделов каталога {0}")
    void pageShouldContainCatalogCategoriesNamesTest(String catalogCategoryName) {
        open(baseUrl);
        $("a[href='/catalog/']").click();
        $$(".name").find(text(catalogCategoryName)).click();
        $("#pagetitle").shouldHave(text(catalogCategoryName));
    }

    @CsvSource({
            "Рюкзак, Рюкзак 44х33х23 см, 2 отделения, вес 424 г, полиэстер \"EasyLine\" ErichKrause",
            "Тетрадь,  Тетрадь предметная 48 л А5 клетка скоба \"Чубрик\" - Химия/5/100",
            "Дневник, Дневник 1-11 классы 48 л \"Compass\" deVENTE, твердый переплет, искусственная кожа"
    })
    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @DisplayName("Проверка поиска канцелярских товаров по каталогу")
    @ParameterizedTest(name = "При вводе в поиск {0} на старнице присутствует текст {1}")
    void successfulSearchTextTest(String testData) {
        open(baseUrl);
        $("#title-searchs-input").setValue(testData).pressEnter();
        $(".item-title").shouldHave(text(testData));
    }

    static Stream<Arguments> catalogSubCategoryListTest() {
        return Stream.of(
                Arguments.of("Канцелярские товары и бумага", List.of("Бумажная продукция", "Организация документов",
                        "Письменные принадлежности", "Мелкоофисные товары", "Настольные наборы",
                        "Калькуляторы", "Офисное оборудование и расходные материалы", "Товары для презентаций")),
                Arguments.of("Расходные материалы", List.of("Фотобумага", "Бумага для плоттера",
                        "Картриджи для принтеров и факсов", "Чиcтящие средства для оргтехники"))
        );
    }

    @MethodSource
    @DisplayName("Проверка наличия подкатегорий каталога канцелярских товаров магазина Офис депо")
    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @ParameterizedTest(name = "Проверка отображения в каталоге подкатегорий для категории {0}")
    void catalogSubCategoryListTest(String catalogCategoryName, List<String> subCategoriesNames) {
        open(baseUrl);
        $("a[href='/catalog/']").click();
        $$(".name").find(text(catalogCategoryName)).click();
        $$(".section_item_inner").filter(visible).shouldHave(CollectionCondition.texts(subCategoriesNames));
    }
}

