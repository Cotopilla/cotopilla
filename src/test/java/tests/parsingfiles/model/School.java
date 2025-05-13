package tests.parsingfiles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class School {

    private String name;
    private Integer number;
    private SchoolAddress address;
    @SerializedName("class_name")
    @JsonProperty("class_name")
    private String[] className;

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public SchoolAddress getAddress() {
        return address;
    }

    public String[] getClassName() {
        return className;
    }
}
