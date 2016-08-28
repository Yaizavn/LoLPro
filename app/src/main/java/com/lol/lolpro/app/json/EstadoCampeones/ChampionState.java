
package com.lol.lolpro.app.json.EstadoCampeones;

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
    "id",
    "active",
    "botEnabled",
    "freeToPlay",
    "botMmEnabled",
    "rankedPlayEnabled"
})
public class ChampionState {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("botEnabled")
    private Boolean botEnabled;
    @JsonProperty("freeToPlay")
    private Boolean freeToPlay;
    @JsonProperty("botMmEnabled")
    private Boolean botMmEnabled;
    @JsonProperty("rankedPlayEnabled")
    private Boolean rankedPlayEnabled;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The active
     */
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * @param active
     *     The active
     */
    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * 
     * @return
     *     The botEnabled
     */
    @JsonProperty("botEnabled")
    public Boolean getBotEnabled() {
        return botEnabled;
    }

    /**
     * 
     * @param botEnabled
     *     The botEnabled
     */
    @JsonProperty("botEnabled")
    public void setBotEnabled(Boolean botEnabled) {
        this.botEnabled = botEnabled;
    }

    /**
     * 
     * @return
     *     The freeToPlay
     */
    @JsonProperty("freeToPlay")
    public Boolean getFreeToPlay() {
        return freeToPlay;
    }

    /**
     * 
     * @param freeToPlay
     *     The freeToPlay
     */
    @JsonProperty("freeToPlay")
    public void setFreeToPlay(Boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    /**
     * 
     * @return
     *     The botMmEnabled
     */
    @JsonProperty("botMmEnabled")
    public Boolean getBotMmEnabled() {
        return botMmEnabled;
    }

    /**
     * 
     * @param botMmEnabled
     *     The botMmEnabled
     */
    @JsonProperty("botMmEnabled")
    public void setBotMmEnabled(Boolean botMmEnabled) {
        this.botMmEnabled = botMmEnabled;
    }

    /**
     * 
     * @return
     *     The rankedPlayEnabled
     */
    @JsonProperty("rankedPlayEnabled")
    public Boolean getRankedPlayEnabled() {
        return rankedPlayEnabled;
    }

    /**
     * 
     * @param rankedPlayEnabled
     *     The rankedPlayEnabled
     */
    @JsonProperty("rankedPlayEnabled")
    public void setRankedPlayEnabled(Boolean rankedPlayEnabled) {
        this.rankedPlayEnabled = rankedPlayEnabled;
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
