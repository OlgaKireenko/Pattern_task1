package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DeliveryOrderData;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryOrderTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {

        String name = DeliveryOrderData.generateName("ru");
        String date1 = DeliveryOrderData.generateDate(3);
        String date2 = DeliveryOrderData.generateDate(10);
        String city = DeliveryOrderData.generateCity("ru");
        String phone = DeliveryOrderData.generatePhone("ru");


        $(".input__control").setValue(city);
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(date1);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $(".checkbox__box").click();
        $(byText("Запланировать")).click();

        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на "+date1));

        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(date2);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на "+date2));
        
    }
}
