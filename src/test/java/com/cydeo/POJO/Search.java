package com.cydeo.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Data
public class Search {
    @JsonProperty("content")
    private List<Spartan> allSpartans;
    private int totalElement;

}
