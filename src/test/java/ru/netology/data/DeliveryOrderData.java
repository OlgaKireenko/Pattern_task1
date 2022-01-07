package ru.netology.data;


import com.github.javafaker.Faker;
//import lombok.Value;
//import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DeliveryOrderData {
    private DeliveryOrderData() {
    }

    public static String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static String generateCity(String locale) {

        Faker faker = new Faker(new Locale("ru"));
        String city = faker.address().cityName();

        return city;
    }

    public static String generateName(String locale) {

        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {

        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().cellPhone();

        return phone;
    }

}

