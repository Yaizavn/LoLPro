
package com.lol.lolpro.app.json.EstadoCampeones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "champions"
})
public class BaseEstadoCampeones {

    @JsonProperty("champions")
    private List<ChampionState> champions = new ArrayList<ChampionState>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The champions
     */
    @JsonProperty("champions")
    public List<ChampionState> getChampions() {
        return champions;
    }

    /**
     * 
     * @param champions
     *     The champions
     */
    @JsonProperty("champions")
    public void setChampions(List<ChampionState> champions) {
        this.champions = champions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
