package ru.netology.sender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {

    private final static Location RUSSIA = new Location("Moscow", Country.RUSSIA, null, 0);
    private final static Location USA = new Location("New York", Country.USA, null, 0);
    private final static Location GERMANY = new Location("Berlin", Country.GERMANY, null, 0);
    private final static Location BRAZIL = new Location("Brasília", Country.BRAZIL, null, 0);
    private final LocalizationService localizationService = Mockito.mock(LocalizationService.class);
    private final GeoService geoService = Mockito.mock(GeoService.class);

    @Before
    public void configureMockito() {
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.BRAZIL)).thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.GERMANY)).thenReturn("Welcome");
    }

    @Test
    public void testRuLocalizationSender() {
        String ipAddress = "172.123.12.19";
        String expected = "Добро пожаловать";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(RUSSIA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assert.assertEquals(messageSender.send(headers), expected);
    }

    @Test
    public void testRuLocalizationSenderNegative() {
        String ipAddress = "172.123.12.19";
        String expected = "Добро пожаловать";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(USA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assert.assertNotEquals(expected, messageSender.send(headers));
    }

    @Test
    public void testEnLocalizationSender() {
        String ipAddress = "96.0.0.0";
        String expected = "Welcome";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(USA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assert.assertEquals(messageSender.send(headers), expected);
    }

    @Test
    public void testEnGermanyLocalizationSender() {
        String ipAddress = "96.0.0.0";
        String expected = "Welcome";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(GERMANY);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assert.assertEquals(messageSender.send(headers), expected);
    }

    @Test
    public void testEnBrazilLocalizationSender() {
        String ipAddress = "96.0.0.0";
        String expected = "Welcome";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(BRAZIL);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assert.assertEquals(messageSender.send(headers), expected);
    }
}
