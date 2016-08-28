
package com.lol.lolpro.app.json.Campeones;

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
    "label",
    "effect"
})
public class Leveltip {

    @JsonProperty("label")
    private List<String> label = new ArrayList<String>();
    @JsonProperty("effect")
    private List<String> effect = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The label
     */
    @JsonProperty("label")
    public List<String> getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    @JsonProperty("label")
    public void setLabel(List<String> label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The effect
     */
    @JsonProperty("effect")
    public List<String> getEffect() {
        return effect;
    }

    /**
     * 
     * @param effect
     *     The effect
     */
    @JsonProperty("effect")
    public void setEffect(List<String> effect) {
        this.effect = effect;
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
