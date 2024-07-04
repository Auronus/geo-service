package ru.netology.geo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.Arrays;
import java.util.Collection;

import static ru.netology.geo.GeoServiceImpl.*;

@RunWith(Parameterized.class)
public class GeoServiceTest {
    private String ipAddress;
    private Location location;

    private static final Location USA_LOCATION = new Location("New York", Country.USA, null, 0);
    private static final Location RUSSIA_LOCATION = new Location("Moscow", Country.RUSSIA, null, 0);

    public GeoServiceTest(String ipAddress, Location location) {
        this.ipAddress = ipAddress;
        this.location = location;
    }

    @Parameterized.Parameters
    public static Collection countries() {
        return Arrays.asList(new Object[][]{
                {LOCALHOST, new Location(null, null, null, 0)},
                {MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)},
                {NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)},
                {"172.111.111.21", RUSSIA_LOCATION},
                {"172.", RUSSIA_LOCATION},
                {"171.111.111.21", null},
                {"173.111.111.21", null},
                {"172.0.0.0", RUSSIA_LOCATION},
                {"96.123.11.11", USA_LOCATION},
                {"96", null},
                {"95.123.11.11", null},
                {"94.123.11.11", null},
                {"96.0.0.0", USA_LOCATION},
                {"17", null},
                {"9", null}
        });
    }

    @Test
    public void testLocationByIp() {
        System.out.println("Parameterized idAdress is : " + ipAddress);
        GeoService geoService = new GeoServiceImpl();
        Assert.assertEquals(location, geoService.byIp(ipAddress));
    }
}
