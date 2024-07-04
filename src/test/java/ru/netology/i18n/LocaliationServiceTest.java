package ru.netology.i18n;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.netology.entity.Country;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class LocaliationServiceTest {
    private Country country;
    private String text;

    public LocaliationServiceTest(Country country, String text) {
        this.country = country;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Collection countries() {
        return Arrays.asList(new Object[][]{
                {Country.RUSSIA, "Добро пожаловать"},
                {Country.USA, "Welcome"},
                {Country.GERMANY, "Welcome"},
                {Country.BRAZIL, "Welcome"},
                {null, "Welcome"}
        });
    }

    @Test
    public void testLocationByIp() {
        System.out.println("Parameterized Country is : " + country);
        LocalizationService localizationService = new LocalizationServiceImpl();
        if (country == null) {
            Assert.assertThrows(NullPointerException.class, () -> localizationService.locale(country));
        } else {
            Assert.assertEquals(text, localizationService.locale(country));
        }
    }
}
