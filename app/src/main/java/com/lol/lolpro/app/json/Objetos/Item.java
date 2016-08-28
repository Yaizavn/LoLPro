
package com.lol.lolpro.app.json.Objetos;

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
    "name",
    "group",
    "description",
    "sanitizedDescription",
    "colloq",
    "plaintext",
    "consumed",
    "stacks",
    "depth",
    "consumeOnFull",
    "from",
    "into",
    "specialRecipe",
    "inStore",
    "hideFromAll",
    "requiredChampion",
    "tags",
    "maps",
    "image",
    "stats",
    "gold",
    "rune"
})
public class Item {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("group")
    private String group;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sanitizedDescription")
    private String sanitizedDescription;
    @JsonProperty("colloq")
    private String colloq;
    @JsonProperty("plaintext")
    private String plaintext;
    @JsonProperty("consumed")
    private Boolean consumed;
    @JsonProperty("stacks")
    private Integer stacks;
    @JsonProperty("depth")
    private Integer depth;
    @JsonProperty("consumeOnFull")
    private Boolean consumeOnFull;
    @JsonProperty("from")
    private List<String> from = new ArrayList<String>();
    @JsonProperty("into")
    private List<String> into = new ArrayList<String>();
    @JsonProperty("specialRecipe")
    private Integer specialRecipe;
    @JsonProperty("inStore")
    private Boolean inStore;
    @JsonProperty("hideFromAll")
    private Boolean hideFromAll;
    @JsonProperty("requiredChampion")
    private String requiredChampion;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("maps")
    private Maps maps;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("gold")
    private Gold gold;
    @JsonProperty("rune")
    private Rune rune;
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
     *     The group
     */
    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
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
     *     The colloq
     */
    @JsonProperty("colloq")
    public String getColloq() {
        return colloq;
    }

    /**
     * 
     * @param colloq
     *     The colloq
     */
    @JsonProperty("colloq")
    public void setColloq(String colloq) {
        this.colloq = colloq;
    }

    /**
     * 
     * @return
     *     The plaintext
     */
    @JsonProperty("plaintext")
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * 
     * @param plaintext
     *     The plaintext
     */
    @JsonProperty("plaintext")
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * 
     * @return
     *     The consumed
     */
    @JsonProperty("consumed")
    public Boolean getConsumed() {
        return consumed;
    }

    /**
     * 
     * @param consumed
     *     The consumed
     */
    @JsonProperty("consumed")
    public void setConsumed(Boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * 
     * @return
     *     The stacks
     */
    @JsonProperty("stacks")
    public Integer getStacks() {
        return stacks;
    }

    /**
     * 
     * @param stacks
     *     The stacks
     */
    @JsonProperty("stacks")
    public void setStacks(Integer stacks) {
        this.stacks = stacks;
    }

    /**
     * 
     * @return
     *     The depth
     */
    @JsonProperty("depth")
    public Integer getDepth() {
        return depth;
    }

    /**
     * 
     * @param depth
     *     The depth
     */
    @JsonProperty("depth")
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * 
     * @return
     *     The consumeOnFull
     */
    @JsonProperty("consumeOnFull")
    public Boolean getConsumeOnFull() {
        return consumeOnFull;
    }

    /**
     * 
     * @param consumeOnFull
     *     The consumeOnFull
     */
    @JsonProperty("consumeOnFull")
    public void setConsumeOnFull(Boolean consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    /**
     * 
     * @return
     *     The from
     */
    @JsonProperty("from")
    public List<String> getFrom() {
        return from;
    }

    /**
     * 
     * @param from
     *     The from
     */
    @JsonProperty("from")
    public void setFrom(List<String> from) {
        this.from = from;
    }

    /**
     * 
     * @return
     *     The into
     */
    @JsonProperty("into")
    public List<String> getInto() {
        return into;
    }

    /**
     * 
     * @param into
     *     The into
     */
    @JsonProperty("into")
    public void setInto(List<String> into) {
        this.into = into;
    }

    /**
     * 
     * @return
     *     The specialRecipe
     */
    @JsonProperty("specialRecipe")
    public Integer getSpecialRecipe() {
        return specialRecipe;
    }

    /**
     * 
     * @param specialRecipe
     *     The specialRecipe
     */
    @JsonProperty("specialRecipe")
    public void setSpecialRecipe(Integer specialRecipe) {
        this.specialRecipe = specialRecipe;
    }

    /**
     * 
     * @return
     *     The inStore
     */
    @JsonProperty("inStore")
    public Boolean getInStore() {
        return inStore;
    }

    /**
     * 
     * @param inStore
     *     The inStore
     */
    @JsonProperty("inStore")
    public void setInStore(Boolean inStore) {
        this.inStore = inStore;
    }

    /**
     * 
     * @return
     *     The hideFromAll
     */
    @JsonProperty("hideFromAll")
    public Boolean getHideFromAll() {
        return hideFromAll;
    }

    /**
     * 
     * @param hideFromAll
     *     The hideFromAll
     */
    @JsonProperty("hideFromAll")
    public void setHideFromAll(Boolean hideFromAll) {
        this.hideFromAll = hideFromAll;
    }

    /**
     * 
     * @return
     *     The requiredChampion
     */
    @JsonProperty("requiredChampion")
    public String getRequiredChampion() {
        return requiredChampion;
    }

    /**
     * 
     * @param requiredChampion
     *     The requiredChampion
     */
    @JsonProperty("requiredChampion")
    public void setRequiredChampion(String requiredChampion) {
        this.requiredChampion = requiredChampion;
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
     *     The maps
     */
    @JsonProperty("maps")
    public Maps getMaps() {
        return maps;
    }

    /**
     * 
     * @param maps
     *     The maps
     */
    @JsonProperty("maps")
    public void setMaps(Maps maps) {
        this.maps = maps;
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
     *     The gold
     */
    @JsonProperty("gold")
    public Gold getGold() {
        return gold;
    }

    /**
     * 
     * @param gold
     *     The gold
     */
    @JsonProperty("gold")
    public void setGold(Gold gold) {
        this.gold = gold;
    }

    /**
     * 
     * @return
     *     The rune
     */
    @JsonProperty("rune")
    public Rune getRune() {
        return rune;
    }

    /**
     * 
     * @param rune
     *     The rune
     */
    @JsonProperty("rune")
    public void setRune(Rune rune) {
        this.rune = rune;
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
