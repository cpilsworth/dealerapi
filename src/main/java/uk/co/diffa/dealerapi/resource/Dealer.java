package uk.co.diffa.dealerapi.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "number", "coordinates"})
public class Dealer {
    private String number;
    private String name;
    private final Double[] coordinates;

    @JsonCreator
    public Dealer(final String number, final String name, Double[] coordinates) {
        this.number = number;
        this.name = name;
        this.coordinates = coordinates;
    }

    @JsonProperty
    public String getNumber() {
        return number;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }
}
