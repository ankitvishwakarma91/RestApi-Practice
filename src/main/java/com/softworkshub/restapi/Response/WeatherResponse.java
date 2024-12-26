
package com.softworkshub.restapi.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {


    private Current current;


    @Getter
    @Setter
    public class Current {

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("observation_time")
        private String observationTime;

        @JsonProperty("temperature")
        private long temperature;

        @JsonProperty("feelslike")
        private long feelslike;

    }
}






