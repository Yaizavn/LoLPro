
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
    "name",
    "description",
    "sanitizedDescription",
    "tooltip",
    "sanitizedTooltip",
    "leveltip",
    "image",
    "resource",
    "maxrank",
    "cost",
    "costType",
    "costBurn",
    "cooldown",
    "cooldownBurn",
    "effect",
    "effectBurn",
    "vars",
    "range",
    "rangeBurn",
    "key",
    "altimages"
})
public class Spell {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sanitizedDescription")
    private String sanitizedDescription;
    @JsonProperty("tooltip")
    private String tooltip;
    @JsonProperty("sanitizedTooltip")
    private String sanitizedTooltip;
    @JsonProperty("leveltip")
    private Leveltip leveltip;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("maxrank")
    private Integer maxrank;
    @JsonProperty("cost")
    private List<Integer> cost = new ArrayList<Integer>();
    @JsonProperty("costType")
    private String costType;
    @JsonProperty("costBurn")
    private String costBurn;
    @JsonProperty("cooldown")
    private List<Double> cooldown = new ArrayList<Double>();
    @JsonProperty("cooldownBurn")
    private String cooldownBurn;
    @JsonProperty("effect")
    private List<Object> effect = new ArrayList<Object>();
    @JsonProperty("effectBurn")
    private List<String> effectBurn = new ArrayList<String>();
    @JsonProperty("vars")
    private List<Var> vars = new ArrayList<Var>();
    @JsonProperty("range")
    private List<Integer> range = new ArrayList<Integer>();
    @JsonProperty("rangeBurn")
    private String rangeBurn;
    @JsonProperty("key")
    private String key;
    @JsonProperty("altimages")
    private List<Image> altimages = new ArrayList<Image>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The sanitizedDescription
     */
    @JsonProperty("sanitizedDescription")
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * 
     * @param sanitizedDescription
     *     The sanitizedDescription
     */
    @JsonProperty("sanitizedDescription")
    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * 
     * @return
     *     The tooltip
     */
    @JsonProperty("tooltip")
    public String getTooltip() {
        return tooltip;
    }

    /**
     * 
     * @param tooltip
     *     The tooltip
     */
    @JsonProperty("tooltip")
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * 
     * @return
     *     The sanitizedTooltip
     */
    @JsonProperty("sanitizedTooltip")
    public String getSanitizedTooltip() {
        return sanitizedTooltip;
    }

    /**
     * 
     * @param sanitizedTooltip
     *     The sanitizedTooltip
     */
    @JsonProperty("sanitizedTooltip")
    public void setSanitizedTooltip(String sanitizedTooltip) {
        this.sanitizedTooltip = sanitizedTooltip;
    }

    /**
     * 
     * @return
     *     The leveltip
     */
    @JsonProperty("leveltip")
    public Leveltip getLeveltip() {
        return leveltip;
    }

    /**
     * 
     * @param leveltip
     *     The leveltip
     */
    @JsonProperty("leveltip")
    public void setLeveltip(Leveltip leveltip) {
        this.leveltip = leveltip;
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
     *     The resource
     */
    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    /**
     * 
     * @param resource
     *     The resource
     */
    @JsonProperty("resource")
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * 
     * @return
     *     The maxrank
     */
    @JsonProperty("maxrank")
    public Integer getMaxrank() {
        return maxrank;
    }

    /**
     * 
     * @param maxrank
     *     The maxrank
     */
    @JsonProperty("maxrank")
    public void setMaxrank(Integer maxrank) {
        this.maxrank = maxrank;
    }

    /**
     * 
     * @return
     *     The cost
     */
    @JsonProperty("cost")
    public List<Integer> getCost() {
        return cost;
    }

    /**
     * 
     * @param cost
     *     The cost
     */
    @JsonProperty("cost")
    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    /**
     * 
     * @return
     *     The costType
     */
    @JsonProperty("costType")
    public String getCostType() {
        return costType;
    }

    /**
     * 
     * @param costType
     *     The costType
     */
    @JsonProperty("costType")
    public void setCostType(String costType) {
        this.costType = costType;
    }

    /**
     * 
     * @return
     *     The costBurn
     */
    @JsonProperty("costBurn")
    public String getCostBurn() {
        return costBurn;
    }

    /**
     * 
     * @param costBurn
     *     The costBurn
     */
    @JsonProperty("costBurn")
    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    /**
     * 
     * @return
     *     The cooldown
     */
    @JsonProperty("cooldown")
    public List<Double> getCooldown() {
        return cooldown;
    }

    /**
     * 
     * @param cooldown
     *     The cooldown
     */
    @JsonProperty("cooldown")
    public void setCooldown(List<Double> cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * 
     * @return
     *     The cooldownBurn
     */
    @JsonProperty("cooldownBurn")
    public String getCooldownBurn() {
        return cooldownBurn;
    }

    /**
     * 
     * @param cooldownBurn
     *     The cooldownBurn
     */
    @JsonProperty("cooldownBurn")
    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    /**
     * 
     * @return
     *     The effect
     */
    @JsonProperty("effect")
    public List<Object> getEffect() {
        return effect;
    }

    /**
     * 
     * @param effect
     *     The effect
     */
    @JsonProperty("effect")
    public void setEffect(List<Object> effect) {
        this.effect = effect;
    }

    /**
     * 
     * @return
     *     The effectBurn
     */
    @JsonProperty("effectBurn")
    public List<String> getEffectBurn() {
        return effectBurn;
    }

    /**
     * 
     * @param effectBurn
     *     The effectBurn
     */
    @JsonProperty("effectBurn")
    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    /**
     * 
     * @return
     *     The vars
     */
    @JsonProperty("vars")
    public List<Var> getVars() {
        return vars;
    }

    /**
     * 
     * @param vars
     *     The vars
     */
    @JsonProperty("vars")
    public void setVars(List<Var> vars) {
        this.vars = vars;
    }

    /**
     * 
     * @return
     *     The range
     */
    @JsonProperty("range")
    public List<Integer> getRange() {
        return range;
    }

    /**
     * 
     * @param range
     *     The range
     */
    @JsonProperty("range")
    public void setRange(List<Integer> range) {
        this.range = range;
    }

    /**
     * 
     * @return
     *     The rangeBurn
     */
    @JsonProperty("rangeBurn")
    public String getRangeBurn() {
        return rangeBurn;
    }

    /**
     * 
     * @param rangeBurn
     *     The rangeBurn
     */
    @JsonProperty("rangeBurn")
    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
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
     *     The altimages
     */
    @JsonProperty("altimages")
    public List<Image> getAltimages() {
        return altimages;
    }

    /**
     * 
     * @param altimages
     *     The altimages
     */
    @JsonProperty("altimages")
    public void setAltimages(List<Image> altimages) {
        this.altimages = altimages;
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
