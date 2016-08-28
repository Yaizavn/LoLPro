
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
    "id",
    "key",
    "name",
    "title",
    "image",
    "skins",
    "lore",
    "blurb",
    "allytips",
    "enemytips",
    "tags",
    "partype",
    "info",
    "stats",
    "spells",
    "passive",
    "recommended"
})
public class Champion {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("skins")
    private List<Skin> skins = new ArrayList<Skin>();
    @JsonProperty("lore")
    private String lore;
    @JsonProperty("blurb")
    private String blurb;
    @JsonProperty("allytips")
    private List<String> allytips = new ArrayList<String>();
    @JsonProperty("enemytips")
    private List<String> enemytips = new ArrayList<String>();
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("partype")
    private String partype;
    @JsonProperty("info")
    private Info info;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("spells")
    private List<Spell> spells = new ArrayList<Spell>();
    @JsonProperty("passive")
    private Passive passive;
    @JsonProperty("recommended")
    private List<Recommended> recommended = new ArrayList<Recommended>();
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
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The skins
     */
    @JsonProperty("skins")
    public List<Skin> getSkins() {
        return skins;
    }

    /**
     * 
     * @param skins
     *     The skins
     */
    @JsonProperty("skins")
    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    /**
     * 
     * @return
     *     The lore
     */
    @JsonProperty("lore")
    public String getLore() {
        return lore;
    }

    /**
     * 
     * @param lore
     *     The lore
     */
    @JsonProperty("lore")
    public void setLore(String lore) {
        this.lore = lore;
    }

    /**
     * 
     * @return
     *     The blurb
     */
    @JsonProperty("blurb")
    public String getBlurb() {
        return blurb;
    }

    /**
     * 
     * @param blurb
     *     The blurb
     */
    @JsonProperty("blurb")
    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    /**
     * 
     * @return
     *     The allytips
     */
    @JsonProperty("allytips")
    public List<String> getAllytips() {
        return allytips;
    }

    /**
     * 
     * @param allytips
     *     The allytips
     */
    @JsonProperty("allytips")
    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    /**
     * 
     * @return
     *     The enemytips
     */
    @JsonProperty("enemytips")
    public List<String> getEnemytips() {
        return enemytips;
    }

    /**
     * 
     * @param enemytips
     *     The enemytips
     */
    @JsonProperty("enemytips")
    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    /**
     * 
     * @return
     *     The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The partype
     */
    @JsonProperty("partype")
    public String getPartype() {
        return partype;
    }

    /**
     * 
     * @param partype
     *     The partype
     */
    @JsonProperty("partype")
    public void setPartype(String partype) {
        this.partype = partype;
    }

    /**
     * 
     * @return
     *     The info
     */
    @JsonProperty("info")
    public Info getInfo() {
        return info;
    }

    /**
     * 
     * @param info
     *     The info
     */
    @JsonProperty("info")
    public void setInfo(Info info) {
        this.info = info;
    }

    /**
     * 
     * @return
     *     The stats
     */
    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    /**
     * 
     * @param stats
     *     The stats
     */
    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * 
     * @return
     *     The spells
     */
    @JsonProperty("spells")
    public List<Spell> getSpells() {
        return spells;
    }

    /**
     * 
     * @param spells
     *     The spells
     */
    @JsonProperty("spells")
    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    /**
     * 
     * @return
     *     The passive
     */
    @JsonProperty("passive")
    public Passive getPassive() {
        return passive;
    }

    /**
     * 
     * @param passive
     *     The passive
     */
    @JsonProperty("passive")
    public void setPassive(Passive passive) {
        this.passive = passive;
    }

    /**
     * 
     * @return
     *     The recommended
     */
    @JsonProperty("recommended")
    public List<Recommended> getRecommended() {
        return recommended;
    }

    /**
     * 
     * @param recommended
     *     The recommended
     */
    @JsonProperty("recommended")
    public void setRecommended(List<Recommended> recommended) {
        this.recommended = recommended;
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
