
package com.lol.lolpro.app.json.Campeones;

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
    "attack",
    "defense",
    "magic",
    "difficulty"
})
public class Info {

    @JsonProperty("attack")
    private Integer attack;
    @JsonProperty("defense")
    private Integer defense;
    @JsonProperty("magic")
    private Integer magic;
    @JsonProperty("difficulty")
    private Integer difficulty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The attack
     */
    @JsonProperty("attack")
    public Integer getAttack() {
        return attack;
    }

    /**
     * 
     * @param attack
     *     The attack
     */
    @JsonProperty("attack")
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     * 
     * @return
     *     The defense
     */
    @JsonProperty("defense")
    public Integer getDefense() {
        return defense;
    }

    /**
     * 
     * @param defense
     *     The defense
     */
    @JsonProperty("defense")
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * 
     * @return
     *     The magic
     */
    @JsonProperty("magic")
    public Integer getMagic() {
        return magic;
    }

    /**
     * 
     * @param magic
     *     The magic
     */
    @JsonProperty("magic")
    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    /**
     * 
     * @return
     *     The difficulty
     */
    @JsonProperty("difficulty")
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * 
     * @param difficulty
     *     The difficulty
     */
    @JsonProperty("difficulty")
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
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
