
package com.lol.lolpro.app.json.Campeones;

import android.os.Parcel;
import android.os.Parcelable;

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
    "armor",
    "armorperlevel",
    "attackdamage",
    "attackdamageperlevel",
    "attackrange",
    "attackspeedoffset",
    "attackspeedperlevel",
    "crit",
    "critperlevel",
    "hp",
    "hpperlevel",
    "hpregen",
    "hpregenperlevel",
    "movespeed",
    "mp",
    "mpperlevel",
    "mpregen",
    "mpregenperlevel",
    "spellblock",
    "spellblockperlevel"
})
public class Stats implements Parcelable {

    @JsonProperty("armor")
    private Double armor;
    @JsonProperty("armorperlevel")
    private Double armorperlevel;
    @JsonProperty("attackdamage")
    private Double attackdamage;
    @JsonProperty("attackdamageperlevel")
    private Double attackdamageperlevel;
    @JsonProperty("attackrange")
    private Double attackrange;
    @JsonProperty("attackspeedoffset")
    private Double attackspeedoffset;
    @JsonProperty("attackspeedperlevel")
    private Double attackspeedperlevel;
    @JsonProperty("crit")
    private Double crit;
    @JsonProperty("critperlevel")
    private Double critperlevel;
    @JsonProperty("hp")
    private Double hp;
    @JsonProperty("hpperlevel")
    private Double hpperlevel;
    @JsonProperty("hpregen")
    private Double hpregen;
    @JsonProperty("hpregenperlevel")
    private Double hpregenperlevel;
    @JsonProperty("movespeed")
    private Double movespeed;
    @JsonProperty("mp")
    private Double mp;
    @JsonProperty("mpperlevel")
    private Double mpperlevel;
    @JsonProperty("mpregen")
    private Double mpregen;
    @JsonProperty("mpregenperlevel")
    private Double mpregenperlevel;
    @JsonProperty("spellblock")
    private Double spellblock;
    @JsonProperty("spellblockperlevel")
    private Double spellblockperlevel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The armor
     */
    @JsonProperty("armor")
    public Double getArmor() {
        return armor;
    }

    /**
     * 
     * @param armor
     *     The armor
     */
    @JsonProperty("armor")
    public void setArmor(Double armor) {
        this.armor = armor;
    }

    /**
     * 
     * @return
     *     The armorperlevel
     */
    @JsonProperty("armorperlevel")
    public Double getArmorperlevel() {
        return armorperlevel;
    }

    /**
     * 
     * @param armorperlevel
     *     The armorperlevel
     */
    @JsonProperty("armorperlevel")
    public void setArmorperlevel(Double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    /**
     * 
     * @return
     *     The attackdamage
     */
    @JsonProperty("attackdamage")
    public Double getAttackdamage() {
        return attackdamage;
    }

    /**
     * 
     * @param attackdamage
     *     The attackdamage
     */
    @JsonProperty("attackdamage")
    public void setAttackdamage(Double attackdamage) {
        this.attackdamage = attackdamage;
    }

    /**
     * 
     * @return
     *     The attackdamageperlevel
     */
    @JsonProperty("attackdamageperlevel")
    public Double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    /**
     * 
     * @param attackdamageperlevel
     *     The attackdamageperlevel
     */
    @JsonProperty("attackdamageperlevel")
    public void setAttackdamageperlevel(Double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    /**
     * 
     * @return
     *     The attackrange
     */
    @JsonProperty("attackrange")
    public Double getAttackrange() {
        return attackrange;
    }

    /**
     * 
     * @param attackrange
     *     The attackrange
     */
    @JsonProperty("attackrange")
    public void setAttackrange(Double attackrange) {
        this.attackrange = attackrange;
    }

    /**
     * 
     * @return
     *     The attackspeedoffset
     */
    @JsonProperty("attackspeedoffset")
    public Double getAttackspeedoffset() {
        return attackspeedoffset;
    }

    /**
     * 
     * @param attackspeedoffset
     *     The attackspeedoffset
     */
    @JsonProperty("attackspeedoffset")
    public void setAttackspeedoffset(Double attackspeedoffset) {
        this.attackspeedoffset = attackspeedoffset;
    }

    /**
     * 
     * @return
     *     The attackspeedperlevel
     */
    @JsonProperty("attackspeedperlevel")
    public Double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    /**
     * 
     * @param attackspeedperlevel
     *     The attackspeedperlevel
     */
    @JsonProperty("attackspeedperlevel")
    public void setAttackspeedperlevel(Double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    /**
     * 
     * @return
     *     The crit
     */
    @JsonProperty("crit")
    public Double getCrit() {
        return crit;
    }

    /**
     * 
     * @param crit
     *     The crit
     */
    @JsonProperty("crit")
    public void setCrit(Double crit) {
        this.crit = crit;
    }

    /**
     * 
     * @return
     *     The critperlevel
     */
    @JsonProperty("critperlevel")
    public Double getCritperlevel() {
        return critperlevel;
    }

    /**
     * 
     * @param critperlevel
     *     The critperlevel
     */
    @JsonProperty("critperlevel")
    public void setCritperlevel(Double critperlevel) {
        this.critperlevel = critperlevel;
    }

    /**
     * 
     * @return
     *     The hp
     */
    @JsonProperty("hp")
    public Double getHp() {
        return hp;
    }

    /**
     * 
     * @param hp
     *     The hp
     */
    @JsonProperty("hp")
    public void setHp(Double hp) {
        this.hp = hp;
    }

    /**
     * 
     * @return
     *     The hpperlevel
     */
    @JsonProperty("hpperlevel")
    public Double getHpperlevel() {
        return hpperlevel;
    }

    /**
     * 
     * @param hpperlevel
     *     The hpperlevel
     */
    @JsonProperty("hpperlevel")
    public void setHpperlevel(Double hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    /**
     * 
     * @return
     *     The hpregen
     */
    @JsonProperty("hpregen")
    public Double getHpregen() {
        return hpregen;
    }

    /**
     * 
     * @param hpregen
     *     The hpregen
     */
    @JsonProperty("hpregen")
    public void setHpregen(Double hpregen) {
        this.hpregen = hpregen;
    }

    /**
     * 
     * @return
     *     The hpregenperlevel
     */
    @JsonProperty("hpregenperlevel")
    public Double getHpregenperlevel() {
        return hpregenperlevel;
    }

    /**
     * 
     * @param hpregenperlevel
     *     The hpregenperlevel
     */
    @JsonProperty("hpregenperlevel")
    public void setHpregenperlevel(Double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    /**
     * 
     * @return
     *     The movespeed
     */
    @JsonProperty("movespeed")
    public Double getMovespeed() {
        return movespeed;
    }

    /**
     * 
     * @param movespeed
     *     The movespeed
     */
    @JsonProperty("movespeed")
    public void setMovespeed(Double movespeed) {
        this.movespeed = movespeed;
    }

    /**
     * 
     * @return
     *     The mp
     */
    @JsonProperty("mp")
    public Double getMp() {
        return mp;
    }

    /**
     * 
     * @param mp
     *     The mp
     */
    @JsonProperty("mp")
    public void setMp(Double mp) {
        this.mp = mp;
    }

    /**
     * 
     * @return
     *     The mpperlevel
     */
    @JsonProperty("mpperlevel")
    public Double getMpperlevel() {
        return mpperlevel;
    }

    /**
     * 
     * @param mpperlevel
     *     The mpperlevel
     */
    @JsonProperty("mpperlevel")
    public void setMpperlevel(Double mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    /**
     * 
     * @return
     *     The mpregen
     */
    @JsonProperty("mpregen")
    public Double getMpregen() {
        return mpregen;
    }

    /**
     * 
     * @param mpregen
     *     The mpregen
     */
    @JsonProperty("mpregen")
    public void setMpregen(Double mpregen) {
        this.mpregen = mpregen;
    }

    /**
     * 
     * @return
     *     The mpregenperlevel
     */
    @JsonProperty("mpregenperlevel")
    public Double getMpregenperlevel() {
        return mpregenperlevel;
    }

    /**
     * 
     * @param mpregenperlevel
     *     The mpregenperlevel
     */
    @JsonProperty("mpregenperlevel")
    public void setMpregenperlevel(Double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    /**
     * 
     * @return
     *     The spellblock
     */
    @JsonProperty("spellblock")
    public Double getSpellblock() {
        return spellblock;
    }

    /**
     * 
     * @param spellblock
     *     The spellblock
     */
    @JsonProperty("spellblock")
    public void setSpellblock(Double spellblock) {
        this.spellblock = spellblock;
    }

    /**
     * 
     * @return
     *     The spellblockperlevel
     */
    @JsonProperty("spellblockperlevel")
    public Double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    /**
     * 
     * @param spellblockperlevel
     *     The spellblockperlevel
     */
    @JsonProperty("spellblockperlevel")
    public void setSpellblockperlevel(Double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.armor);
        dest.writeValue(this.armorperlevel);
        dest.writeValue(this.attackdamage);
        dest.writeValue(this.attackdamageperlevel);
        dest.writeValue(this.attackrange);
        dest.writeValue(this.attackspeedoffset);
        dest.writeValue(this.attackspeedperlevel);
        dest.writeValue(this.crit);
        dest.writeValue(this.critperlevel);
        dest.writeValue(this.hp);
        dest.writeValue(this.hpperlevel);
        dest.writeValue(this.hpregen);
        dest.writeValue(this.hpregenperlevel);
        dest.writeValue(this.movespeed);
        dest.writeValue(this.mp);
        dest.writeValue(this.mpperlevel);
        dest.writeValue(this.mpregen);
        dest.writeValue(this.mpregenperlevel);
        dest.writeValue(this.spellblock);
        dest.writeValue(this.spellblockperlevel);
        dest.writeValue(this.additionalProperties);
    }

    public Stats() {
    }

    protected Stats(Parcel in) {
        this.armor = (Double) in.readValue(Double.class.getClassLoader());
        this.armorperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.attackdamage = (Double) in.readValue(Double.class.getClassLoader());
        this.attackdamageperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.attackrange = (Double) in.readValue(Double.class.getClassLoader());
        this.attackspeedoffset = (Double) in.readValue(Double.class.getClassLoader());
        this.attackspeedperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.crit = (Double) in.readValue(Double.class.getClassLoader());
        this.critperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.hp = (Double) in.readValue(Double.class.getClassLoader());
        this.hpperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.hpregen = (Double) in.readValue(Double.class.getClassLoader());
        this.hpregenperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.movespeed = (Double) in.readValue(Double.class.getClassLoader());
        this.mp = (Double) in.readValue(Double.class.getClassLoader());
        this.mpperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.mpregen = (Double) in.readValue(Double.class.getClassLoader());
        this.mpregenperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.spellblock = (Double) in.readValue(Double.class.getClassLoader());
        this.spellblockperlevel = (Double) in.readValue(Double.class.getClassLoader());
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public static final Parcelable.Creator<Stats> CREATOR = new Parcelable.Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel source) {
            return new Stats(source);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };
}
