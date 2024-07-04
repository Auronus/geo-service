package ru.netology.entity;

import java.util.Objects;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country=" + country +
                ", street='" + street + '\'' +
                ", builing=" + builing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return builing == location.builing
                && (city == location.city || city != null && location.city != null && city.equals(location.city))
                && country == location.country
                && (street == location.street || street != null && location.street != null && street.equals(location.street));
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, street, builing);
    }
}
