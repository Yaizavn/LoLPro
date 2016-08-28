
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
    "key",
    "MaxGroupOwnable"
})
public class Group {

    @JsonProperty("key")
    private String key;
    @JsonProperty("MaxGroupOwnable")
    private String maxGroupOwnable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The maxGroupOwnable
     */
    @JsonProperty("MaxGroupOwnable")
    public String getMaxGroupOwnable() {
        return maxGroupOwnable;
    }

    /**
     * 
     * @param maxGroupOwnable
     *     The MaxGroupOwnable
     */
    @JsonProperty("MaxGroupOwnable")
    public void setMaxGroupOwnable(String maxGroupOwnable) {
        this.maxGroupOwnable = maxGroupOwnable;
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
