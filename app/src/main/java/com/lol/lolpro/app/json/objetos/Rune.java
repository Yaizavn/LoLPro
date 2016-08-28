
package com.lol.lolpro.app.json.Objetos;

import java.util.HashMap;
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
    "isRune",
    "tier",
    "type"
})
public class Rune {

    @JsonProperty("isRune")
    private Boolean isRune;
    @JsonProperty("tier")
    private String tier;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The isRune
     */
    @JsonProperty("isRune")
    public Boolean getIsRune() {
        return isRune;
    }

    /**
     * 
     * @param isRune
     *     The isRune
     */
    @JsonProperty("isRune")
    public void setIsRune(Boolean isRune) {
        this.isRune = isRune;
    }

    /**
     * 
     * @return
     *     The tier
     */
    @JsonProperty("tier")
    public String getTier() {
        return tier;
    }

    /**
     * 
     * @param tier
     *     The tier
     */
    @JsonProperty("tier")
    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
