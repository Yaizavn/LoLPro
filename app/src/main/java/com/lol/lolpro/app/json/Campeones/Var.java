
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
    "key",
    "link",
    "coeff",
    "dyn",
    "ranksWith"
})
public class Var {

    @JsonProperty("key")
    private String key;
    @JsonProperty("link")
    private String link;
    @JsonProperty("coeff")
    private List<Double> coeff = new ArrayList<Double>();
    @JsonProperty("dyn")
    private String dyn;
    @JsonProperty("ranksWith")
    private String ranksWith;
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
     *     The link
     */
    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The coeff
     */
    @JsonProperty("coeff")
    public List<Double> getCoeff() {
        return coeff;
    }

    /**
     * 
     * @param coeff
     *     The coeff
     */
    @JsonProperty("coeff")
    public void setCoeff(List<Double> coeff) {
        this.coeff = coeff;
    }

    /**
     * 
     * @return
     *     The dyn
     */
    @JsonProperty("dyn")
    public String getDyn() {
        return dyn;
    }

    /**
     * 
     * @param dyn
     *     The dyn
     */
    @JsonProperty("dyn")
    public void setDyn(String dyn) {
        this.dyn = dyn;
    }

    /**
     * 
     * @return
     *     The ranksWith
     */
    @JsonProperty("ranksWith")
    public String getRanksWith() {
        return ranksWith;
    }

    /**
     * 
     * @param ranksWith
     *     The ranksWith
     */
    @JsonProperty("ranksWith")
    public void setRanksWith(String ranksWith) {
        this.ranksWith = ranksWith;
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
