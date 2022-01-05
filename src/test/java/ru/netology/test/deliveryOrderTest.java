package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.deliveryOrderData;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class deliveryOrderTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {

        String name = deliveryOrderData.generateName("ru");
        String date1 = deliveryOrderData.generateDate(3);
        String date2 = deliveryOrderData.generateDate(10);
        String city = deliveryOrderData.generateCity("ru");
        String phone = deliveryOrderData.generatePhone("ru");


        $(".input__control").setValue(city);
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(date1);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $(".checkbox__box").click();
        $(byText("Запланировать")).click();

        String text = $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).getText();
        String text2 = "Встреча успешно запланирована на " + date1;
        assertEquals(text2,text);

        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[type='tel']").setValue(date2);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        String textReorder = $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).getText();
        String text3 = "Встреча успешно запланирована на " + date2;
        assertEquals(text3,textReorder);
        //


        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе


        
    }
}
