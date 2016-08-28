
package com.lol.lolpro.app.json.Realm;

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
    "item",
    "rune",
    "mastery",
    "summoner",
    "champion",
    "profileicon",
    "map",
    "language"
})
public class N {

    @JsonProperty("item")
    private String item;
    @JsonProperty("rune")
    private String rune;
    @JsonProperty("mastery")
    private String mastery;
    @JsonProperty("summoner")
    private String summoner;
    @JsonProperty("champion")
    private String champion;
    @JsonProperty("profileicon")
    private String profileicon;
    @JsonProperty("map")
    private String map;
    @JsonProperty("language")
    private String language;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The item
     */
    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    /**
     * 
     * @param item
     *     The item
     */
    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * 
     * @return
     *     The rune
     */
    @JsonProperty("rune")
    public String getRune() {
        return rune;
    }

    /**
     * 
     * @param rune
     *     The rune
     */
    @JsonProperty("rune")
    public void setRune(String rune) {
        this.rune = rune;
    }

    /**
     * 
     * @return
     *     The mastery
     */
    @JsonProperty("mastery")
    public String getMastery() {
        return mastery;
    }

    /**
     * 
     * @param mastery
     *     The mastery
     */
    @JsonProperty("mastery")
    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    /**
     * 
     * @return
     *     The summoner
     */
    @JsonProperty("summoner")
    public String getSummoner() {
        return summoner;
    }

    /**
     * 
     * @param summoner
     *     The summoner
     */
    @JsonProperty("summoner")
    public void setSummoner(String summoner) {
        this.summoner = summoner;
    }

    /**
     * 
     * @return
     *     The champion
     */
    @JsonProperty("champion")
    public String getChampion() {
        return champion;
    }

    /**
     * 
     * @param champion
     *     The champion
     */
    @JsonProperty("champion")
    public void setChampion(String champion) {
        this.champion = champion;
    }

    /**
     * 
     * @return
     *     The profileicon
     */
    @JsonProperty("profileicon")
    public String getProfileicon() {
        return profileicon;
    }

    /**
     * 
     * @param profileicon
     *     The profileicon
     */
    @JsonProperty("profileicon")
    public void setProfileicon(String profileicon) {
        this.profileicon = profileicon;
    }

    /**
     * 
     * @return
     *     The map
     */
    @JsonProperty("map")
    public String getMap() {
        return map;
    }

    /**
     * 
     * @param map
     *     The map
     */
    @JsonProperty("map")
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
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
