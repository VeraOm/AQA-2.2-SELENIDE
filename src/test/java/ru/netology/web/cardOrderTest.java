package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class cardOrderTest {

    @Test
    void cardOrderDeliveryFormTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Омск");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat rusDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateMeeting = rusDateFormat.format(calendar.getTime());
        SelenideElement dateElement = $("[data-test-id=date] input[class=input__control]");
        dateElement.sendKeys("\b\b\b\b\b\b\b\b\b\b");
        dateElement.setValue(dateMeeting);

        $("[data-test-id=name] input").setValue("Вера");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=notification]")
                .$(withText("Встреча успешно забронирована"))
                .waitUntil(visible, 15000);

    }
}
