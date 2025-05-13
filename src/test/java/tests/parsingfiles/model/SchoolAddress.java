package tests.parsingfiles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class SchoolAddress {

    @SerializedName("Street")
    @JsonProperty("Street")
    private String street;
    @SerializedName("City")
    @JsonProperty("City")
    private String city;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
