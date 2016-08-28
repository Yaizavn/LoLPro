
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
    "champion",
    "title",
    "type",
    "map",
    "mode",
    "priority",
    "blocks"
})
public class Recommended {

    @JsonProperty("champion")
    private String champion;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("map")
    private String map;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("priority")
    private Boolean priority;
    @JsonProperty("blocks")
    private List<Block> blocks = new ArrayList<Block>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The mode
     */
    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    /**
     * 
     * @param mode
     *     The mode
     */
    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
    * 
    * @return
    * The priority
    */
    @JsonProperty("priority")
    public Boolean getPriority() {
    return priority;
    }

    /**
    * 
    * @param priority
    * The priority
    */
    @JsonProperty("priority")
    public void setPriority(Boolean priority) {
    this.priority = priority;
    }

    /**
     * 
     * @return
     *     The blocks
     */
    @JsonProperty("blocks")
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * 
     * @param blocks
     *     The blocks
     */
    @JsonProperty("blocks")
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
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
