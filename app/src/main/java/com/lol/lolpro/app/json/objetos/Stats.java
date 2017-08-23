
package com.lol.lolpro.app.json.Objetos;

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
    "rFlatArmorModPerLevel",
    "rFlatArmorPenetrationMod",
    "rFlatArmorPenetrationModPerLevel",
    "rFlatCritChanceModPerLevel",
    "rFlatCritDamageModPerLevel",
    "rFlatEnergyRegenModPerLevel",
    "rFlatDodgeMod",
    "rFlatDodgeModPerLevel",
    "rFlatGoldPer10Mod",
    "rFlatHPModPerLevel",
    "rFlatHPRegenModPerLevel",
    "rFlatMPRegenModPerLevel",
    "rFlatMagicDamageModPerLevel",
    "rFlatMagicPenetrationMod",
    "rFlatMagicPenetrationModPerLevel",
    "rFlatMPModPerLevel",
    "rFlatEnergyModPerLevel",
    "rFlatMovementSpeedModPerLevel",
    "rFlatPhysicalDamageModPerLevel",
    "rFlatSpellBlockModPerLevel",
    "rFlatTimeDeadMod",
    "rFlatTimeDeadModPerLevel",
    "rPercentArmorPenetrationMod",
    "rPercentArmorPenetrationModPerLevel",
    "rPercentAttackSpeedModPerLevel",
    "rPercentCooldownMod",
    "rPercentCooldownModPerLevel",
    "rPercentMagicPenetrationMod",
    "rPercentMagicPenetrationModPerLevel",
    "rPercentMovementSpeedModPerLevel",
    "rPercentTimeDeadMod",
    "rPercentTimeDeadModPerLevel",
    "FlatArmorMod",
    "FlatAttackSpeedMod",
    "FlatBlockMod",
    "FlatCritChanceMod",
    "FlatCritDamageMod",
    "FlatEnergyRegenMod",
    "FlatEnergyPoolMod",
    "FlatEXPBonus",
    "FlatHPPoolMod",
    "FlatHPRegenMod",
    "FlatMPPoolMod",
    "FlatMPRegenMod",
    "FlatMagicDamageMod",
    "FlatMovementSpeedMod",
    "FlatPhysicalDamageMod",
    "FlatSpellBlockMod",
    "PercentArmorMod",
    "PercentAttackSpeedMod",
    "PercentBlockMod",
    "PercentCritChanceMod",
    "PercentCritDamageMod",
    "PercentDodgeMod",
    "PercentEXPBonus",
    "PercentHPPoolMod",
    "PercentHPRegenMod",
    "PercentMPPoolMod",
    "PercentMPRegenMod",
    "PercentMagicDamageMod",
    "PercentMovementSpeedMod",
    "PercentPhysicalDamageMod",
    "PercentSpellBlockMod",
    "PercentSpellVampMod",
    "PercentLifeStealMod"
})
public class Stats implements Parcelable {

    @JsonProperty("rFlatArmorModPerLevel")
    private Double rFlatArmorModPerLevel;
    @JsonProperty("rFlatArmorPenetrationMod")
    private Double rFlatArmorPenetrationMod;
    @JsonProperty("rFlatArmorPenetrationModPerLevel")
    private Double rFlatArmorPenetrationModPerLevel;
    @JsonProperty("rFlatCritChanceModPerLevel")
    private Double rFlatCritChanceModPerLevel;
    @JsonProperty("rFlatCritDamageModPerLevel")
    private Double rFlatCritDamageModPerLevel;
    @JsonProperty("rFlatEnergyRegenModPerLevel")
    private Double rFlatEnergyRegenModPerLevel;
    @JsonProperty("rFlatDodgeMod")
    private Double rFlatDodgeMod;
    @JsonProperty("rFlatDodgeModPerLevel")
    private Double rFlatDodgeModPerLevel;
    @JsonProperty("rFlatGoldPer10Mod")
    private Double rFlatGoldPer10Mod;
    @JsonProperty("rFlatHPModPerLevel")
    private Double rFlatHPModPerLevel;
    @JsonProperty("rFlatHPRegenModPerLevel")
    private Double rFlatHPRegenModPerLevel;
    @JsonProperty("rFlatMPRegenModPerLevel")
    private Double rFlatMPRegenModPerLevel;
    @JsonProperty("rFlatMagicDamageModPerLevel")
    private Double rFlatMagicDamageModPerLevel;
    @JsonProperty("rFlatMagicPenetrationMod")
    private Double rFlatMagicPenetrationMod;
    @JsonProperty("rFlatMagicPenetrationModPerLevel")
    private Double rFlatMagicPenetrationModPerLevel;
    @JsonProperty("rFlatMPModPerLevel")
    private Double rFlatMPModPerLevel;
    @JsonProperty("rFlatEnergyModPerLevel")
    private Double rFlatEnergyModPerLevel;
    @JsonProperty("rFlatMovementSpeedModPerLevel")
    private Double rFlatMovementSpeedModPerLevel;
    @JsonProperty("rFlatPhysicalDamageModPerLevel")
    private Double rFlatPhysicalDamageModPerLevel;
    @JsonProperty("rFlatSpellBlockModPerLevel")
    private Double rFlatSpellBlockModPerLevel;
    @JsonProperty("rFlatTimeDeadMod")
    private Double rFlatTimeDeadMod;
    @JsonProperty("rFlatTimeDeadModPerLevel")
    private Double rFlatTimeDeadModPerLevel;
    @JsonProperty("rPercentArmorPenetrationMod")
    private Double rPercentArmorPenetrationMod;
    @JsonProperty("rPercentArmorPenetrationModPerLevel")
    private Double rPercentArmorPenetrationModPerLevel;
    @JsonProperty("rPercentAttackSpeedModPerLevel")
    private Double rPercentAttackSpeedModPerLevel;
    @JsonProperty("rPercentCooldownMod")
    private Double rPercentCooldownMod;
    @JsonProperty("rPercentCooldownModPerLevel")
    private Double rPercentCooldownModPerLevel;
    @JsonProperty("rPercentMagicPenetrationMod")
    private Double rPercentMagicPenetrationMod;
    @JsonProperty("rPercentMagicPenetrationModPerLevel")
    private Double rPercentMagicPenetrationModPerLevel;
    @JsonProperty("rPercentMovementSpeedModPerLevel")
    private Double rPercentMovementSpeedModPerLevel;
    @JsonProperty("rPercentTimeDeadMod")
    private Double rPercentTimeDeadMod;
    @JsonProperty("rPercentTimeDeadModPerLevel")
    private Double rPercentTimeDeadModPerLevel;
    @JsonProperty("FlatArmorMod")
    private Double flatArmorMod;
    @JsonProperty("FlatAttackSpeedMod")
    private Double flatAttackSpeedMod;
    @JsonProperty("FlatBlockMod")
    private Double flatBlockMod;
    @JsonProperty("FlatCritChanceMod")
    private Double flatCritChanceMod;
    @JsonProperty("FlatCritDamageMod")
    private Double flatCritDamageMod;
    @JsonProperty("FlatEnergyRegenMod")
    private Double flatEnergyRegenMod;
    @JsonProperty("FlatEnergyPoolMod")
    private Double flatEnergyPoolMod;
    @JsonProperty("FlatEXPBonus")
    private Double flatEXPBonus;
    @JsonProperty("FlatHPPoolMod")
    private Double flatHPPoolMod;
    @JsonProperty("FlatHPRegenMod")
    private Double flatHPRegenMod;
    @JsonProperty("FlatMPPoolMod")
    private Double flatMPPoolMod;
    @JsonProperty("FlatMPRegenMod")
    private Double flatMPRegenMod;
    @JsonProperty("FlatMagicDamageMod")
    private Double flatMagicDamageMod;
    @JsonProperty("FlatMovementSpeedMod")
    private Double flatMovementSpeedMod;
    @JsonProperty("FlatPhysicalDamageMod")
    private Double flatPhysicalDamageMod;
    @JsonProperty("FlatSpellBlockMod")
    private Double flatSpellBlockMod;
    @JsonProperty("PercentArmorMod")
    private Double percentArmorMod;
    @JsonProperty("PercentAttackSpeedMod")
    private Double percentAttackSpeedMod;
    @JsonProperty("PercentBlockMod")
    private Double percentBlockMod;
    @JsonProperty("PercentCritChanceMod")
    private Double percentCritChanceMod;
    @JsonProperty("PercentCritDamageMod")
    private Double percentCritDamageMod;
    @JsonProperty("PercentDodgeMod")
    private Double percentDodgeMod;
    @JsonProperty("PercentEXPBonus")
    private Double percentEXPBonus;
    @JsonProperty("PercentHPPoolMod")
    private Double percentHPPoolMod;
    @JsonProperty("PercentHPRegenMod")
    private Double percentHPRegenMod;
    @JsonProperty("PercentMPPoolMod")
    private Double percentMPPoolMod;
    @JsonProperty("PercentMPRegenMod")
    private Double percentMPRegenMod;
    @JsonProperty("PercentMagicDamageMod")
    private Double percentMagicDamageMod;
    @JsonProperty("PercentMovementSpeedMod")
    private Double percentMovementSpeedMod;
    @JsonProperty("PercentPhysicalDamageMod")
    private Double percentPhysicalDamageMod;
    @JsonProperty("PercentSpellBlockMod")
    private Double percentSpellBlockMod;
    @JsonProperty("PercentSpellVampMod")
    private Double percentSpellVampMod;
    @JsonProperty("PercentLifeStealMod")
    private Double percentLifeStealMod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The rFlatArmorModPerLevel
     */
    @JsonProperty("rFlatArmorModPerLevel")
    public Double getRFlatArmorModPerLevel() {
        return rFlatArmorModPerLevel;
    }

    /**
     * 
     * @param rFlatArmorModPerLevel
     *     The rFlatArmorModPerLevel
     */
    @JsonProperty("rFlatArmorModPerLevel")
    public void setRFlatArmorModPerLevel(Double rFlatArmorModPerLevel) {
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatArmorPenetrationMod
     */
    @JsonProperty("rFlatArmorPenetrationMod")
    public Double getRFlatArmorPenetrationMod() {
        return rFlatArmorPenetrationMod;
    }

    /**
     * 
     * @param rFlatArmorPenetrationMod
     *     The rFlatArmorPenetrationMod
     */
    @JsonProperty("rFlatArmorPenetrationMod")
    public void setRFlatArmorPenetrationMod(Double rFlatArmorPenetrationMod) {
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
    }

    /**
     * 
     * @return
     *     The rFlatArmorPenetrationModPerLevel
     */
    @JsonProperty("rFlatArmorPenetrationModPerLevel")
    public Double getRFlatArmorPenetrationModPerLevel() {
        return rFlatArmorPenetrationModPerLevel;
    }

    /**
     * 
     * @param rFlatArmorPenetrationModPerLevel
     *     The rFlatArmorPenetrationModPerLevel
     */
    @JsonProperty("rFlatArmorPenetrationModPerLevel")
    public void setRFlatArmorPenetrationModPerLevel(Double rFlatArmorPenetrationModPerLevel) {
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatCritChanceModPerLevel
     */
    @JsonProperty("rFlatCritChanceModPerLevel")
    public Double getRFlatCritChanceModPerLevel() {
        return rFlatCritChanceModPerLevel;
    }

    /**
     * 
     * @param rFlatCritChanceModPerLevel
     *     The rFlatCritChanceModPerLevel
     */
    @JsonProperty("rFlatCritChanceModPerLevel")
    public void setRFlatCritChanceModPerLevel(Double rFlatCritChanceModPerLevel) {
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatCritDamageModPerLevel
     */
    @JsonProperty("rFlatCritDamageModPerLevel")
    public Double getRFlatCritDamageModPerLevel() {
        return rFlatCritDamageModPerLevel;
    }

    /**
     * 
     * @param rFlatCritDamageModPerLevel
     *     The rFlatCritDamageModPerLevel
     */
    @JsonProperty("rFlatCritDamageModPerLevel")
    public void setRFlatCritDamageModPerLevel(Double rFlatCritDamageModPerLevel) {
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatEnergyRegenModPerLevel
     */
    @JsonProperty("rFlatEnergyRegenModPerLevel")
    public Double getRFlatEnergyRegenModPerLevel() {
        return rFlatEnergyRegenModPerLevel;
    }

    /**
     * 
     * @param rFlatEnergyRegenModPerLevel
     *     The rFlatEnergyRegenModPerLevel
     */
    @JsonProperty("rFlatEnergyRegenModPerLevel")
    public void setRFlatEnergyRegenModPerLevel(Double rFlatEnergyRegenModPerLevel) {
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatDodgeMod
     */
    @JsonProperty("rFlatDodgeMod")
    public Double getRFlatDodgeMod() {
        return rFlatDodgeMod;
    }

    /**
     * 
     * @param rFlatDodgeMod
     *     The rFlatDodgeMod
     */
    @JsonProperty("rFlatDodgeMod")
    public void setRFlatDodgeMod(Double rFlatDodgeMod) {
        this.rFlatDodgeMod = rFlatDodgeMod;
    }

    /**
     * 
     * @return
     *     The rFlatDodgeModPerLevel
     */
    @JsonProperty("rFlatDodgeModPerLevel")
    public Double getRFlatDodgeModPerLevel() {
        return rFlatDodgeModPerLevel;
    }

    /**
     * 
     * @param rFlatDodgeModPerLevel
     *     The rFlatDodgeModPerLevel
     */
    @JsonProperty("rFlatDodgeModPerLevel")
    public void setRFlatDodgeModPerLevel(Double rFlatDodgeModPerLevel) {
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatGoldPer10Mod
     */
    @JsonProperty("rFlatGoldPer10Mod")
    public Double getRFlatGoldPer10Mod() {
        return rFlatGoldPer10Mod;
    }

    /**
     * 
     * @param rFlatGoldPer10Mod
     *     The rFlatGoldPer10Mod
     */
    @JsonProperty("rFlatGoldPer10Mod")
    public void setRFlatGoldPer10Mod(Double rFlatGoldPer10Mod) {
        this.rFlatGoldPer10Mod = rFlatGoldPer10Mod;
    }

    /**
     * 
     * @return
     *     The rFlatHPModPerLevel
     */
    @JsonProperty("rFlatHPModPerLevel")
    public Double getRFlatHPModPerLevel() {
        return rFlatHPModPerLevel;
    }

    /**
     * 
     * @param rFlatHPModPerLevel
     *     The rFlatHPModPerLevel
     */
    @JsonProperty("rFlatHPModPerLevel")
    public void setRFlatHPModPerLevel(Double rFlatHPModPerLevel) {
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatHPRegenModPerLevel
     */
    @JsonProperty("rFlatHPRegenModPerLevel")
    public Double getRFlatHPRegenModPerLevel() {
        return rFlatHPRegenModPerLevel;
    }

    /**
     * 
     * @param rFlatHPRegenModPerLevel
     *     The rFlatHPRegenModPerLevel
     */
    @JsonProperty("rFlatHPRegenModPerLevel")
    public void setRFlatHPRegenModPerLevel(Double rFlatHPRegenModPerLevel) {
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatMPRegenModPerLevel
     */
    @JsonProperty("rFlatMPRegenModPerLevel")
    public Double getRFlatMPRegenModPerLevel() {
        return rFlatMPRegenModPerLevel;
    }

    /**
     * 
     * @param rFlatMPRegenModPerLevel
     *     The rFlatMPRegenModPerLevel
     */
    @JsonProperty("rFlatMPRegenModPerLevel")
    public void setRFlatMPRegenModPerLevel(Double rFlatMPRegenModPerLevel) {
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatMagicDamageModPerLevel
     */
    @JsonProperty("rFlatMagicDamageModPerLevel")
    public Double getRFlatMagicDamageModPerLevel() {
        return rFlatMagicDamageModPerLevel;
    }

    /**
     * 
     * @param rFlatMagicDamageModPerLevel
     *     The rFlatMagicDamageModPerLevel
     */
    @JsonProperty("rFlatMagicDamageModPerLevel")
    public void setRFlatMagicDamageModPerLevel(Double rFlatMagicDamageModPerLevel) {
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatMagicPenetrationMod
     */
    @JsonProperty("rFlatMagicPenetrationMod")
    public Double getRFlatMagicPenetrationMod() {
        return rFlatMagicPenetrationMod;
    }

    /**
     * 
     * @param rFlatMagicPenetrationMod
     *     The rFlatMagicPenetrationMod
     */
    @JsonProperty("rFlatMagicPenetrationMod")
    public void setRFlatMagicPenetrationMod(Double rFlatMagicPenetrationMod) {
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
    }

    /**
     * 
     * @return
     *     The rFlatMagicPenetrationModPerLevel
     */
    @JsonProperty("rFlatMagicPenetrationModPerLevel")
    public Double getRFlatMagicPenetrationModPerLevel() {
        return rFlatMagicPenetrationModPerLevel;
    }

    /**
     * 
     * @param rFlatMagicPenetrationModPerLevel
     *     The rFlatMagicPenetrationModPerLevel
     */
    @JsonProperty("rFlatMagicPenetrationModPerLevel")
    public void setRFlatMagicPenetrationModPerLevel(Double rFlatMagicPenetrationModPerLevel) {
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatMPModPerLevel
     */
    @JsonProperty("rFlatMPModPerLevel")
    public Double getRFlatMPModPerLevel() {
        return rFlatMPModPerLevel;
    }

    /**
     * 
     * @param rFlatMPModPerLevel
     *     The rFlatMPModPerLevel
     */
    @JsonProperty("rFlatMPModPerLevel")
    public void setRFlatMPModPerLevel(Double rFlatMPModPerLevel) {
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatEnergyModPerLevel
     */
    @JsonProperty("rFlatEnergyModPerLevel")
    public Double getRFlatEnergyModPerLevel() {
        return rFlatEnergyModPerLevel;
    }

    /**
     * 
     * @param rFlatEnergyModPerLevel
     *     The rFlatEnergyModPerLevel
     */
    @JsonProperty("rFlatEnergyModPerLevel")
    public void setRFlatEnergyModPerLevel(Double rFlatEnergyModPerLevel) {
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatMovementSpeedModPerLevel
     */
    @JsonProperty("rFlatMovementSpeedModPerLevel")
    public Double getRFlatMovementSpeedModPerLevel() {
        return rFlatMovementSpeedModPerLevel;
    }

    /**
     * 
     * @param rFlatMovementSpeedModPerLevel
     *     The rFlatMovementSpeedModPerLevel
     */
    @JsonProperty("rFlatMovementSpeedModPerLevel")
    public void setRFlatMovementSpeedModPerLevel(Double rFlatMovementSpeedModPerLevel) {
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatPhysicalDamageModPerLevel
     */
    @JsonProperty("rFlatPhysicalDamageModPerLevel")
    public Double getRFlatPhysicalDamageModPerLevel() {
        return rFlatPhysicalDamageModPerLevel;
    }

    /**
     * 
     * @param rFlatPhysicalDamageModPerLevel
     *     The rFlatPhysicalDamageModPerLevel
     */
    @JsonProperty("rFlatPhysicalDamageModPerLevel")
    public void setRFlatPhysicalDamageModPerLevel(Double rFlatPhysicalDamageModPerLevel) {
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatSpellBlockModPerLevel
     */
    @JsonProperty("rFlatSpellBlockModPerLevel")
    public Double getRFlatSpellBlockModPerLevel() {
        return rFlatSpellBlockModPerLevel;
    }

    /**
     * 
     * @param rFlatSpellBlockModPerLevel
     *     The rFlatSpellBlockModPerLevel
     */
    @JsonProperty("rFlatSpellBlockModPerLevel")
    public void setRFlatSpellBlockModPerLevel(Double rFlatSpellBlockModPerLevel) {
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
    }

    /**
     * 
     * @return
     *     The rFlatTimeDeadMod
     */
    @JsonProperty("rFlatTimeDeadMod")
    public Double getRFlatTimeDeadMod() {
        return rFlatTimeDeadMod;
    }

    /**
     * 
     * @param rFlatTimeDeadMod
     *     The rFlatTimeDeadMod
     */
    @JsonProperty("rFlatTimeDeadMod")
    public void setRFlatTimeDeadMod(Double rFlatTimeDeadMod) {
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
    }

    /**
     * 
     * @return
     *     The rFlatTimeDeadModPerLevel
     */
    @JsonProperty("rFlatTimeDeadModPerLevel")
    public Double getRFlatTimeDeadModPerLevel() {
        return rFlatTimeDeadModPerLevel;
    }

    /**
     * 
     * @param rFlatTimeDeadModPerLevel
     *     The rFlatTimeDeadModPerLevel
     */
    @JsonProperty("rFlatTimeDeadModPerLevel")
    public void setRFlatTimeDeadModPerLevel(Double rFlatTimeDeadModPerLevel) {
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentArmorPenetrationMod
     */
    @JsonProperty("rPercentArmorPenetrationMod")
    public Double getRPercentArmorPenetrationMod() {
        return rPercentArmorPenetrationMod;
    }

    /**
     * 
     * @param rPercentArmorPenetrationMod
     *     The rPercentArmorPenetrationMod
     */
    @JsonProperty("rPercentArmorPenetrationMod")
    public void setRPercentArmorPenetrationMod(Double rPercentArmorPenetrationMod) {
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
    }

    /**
     * 
     * @return
     *     The rPercentArmorPenetrationModPerLevel
     */
    @JsonProperty("rPercentArmorPenetrationModPerLevel")
    public Double getRPercentArmorPenetrationModPerLevel() {
        return rPercentArmorPenetrationModPerLevel;
    }

    /**
     * 
     * @param rPercentArmorPenetrationModPerLevel
     *     The rPercentArmorPenetrationModPerLevel
     */
    @JsonProperty("rPercentArmorPenetrationModPerLevel")
    public void setRPercentArmorPenetrationModPerLevel(Double rPercentArmorPenetrationModPerLevel) {
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentAttackSpeedModPerLevel
     */
    @JsonProperty("rPercentAttackSpeedModPerLevel")
    public Double getRPercentAttackSpeedModPerLevel() {
        return rPercentAttackSpeedModPerLevel;
    }

    /**
     * 
     * @param rPercentAttackSpeedModPerLevel
     *     The rPercentAttackSpeedModPerLevel
     */
    @JsonProperty("rPercentAttackSpeedModPerLevel")
    public void setRPercentAttackSpeedModPerLevel(Double rPercentAttackSpeedModPerLevel) {
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentCooldownMod
     */
    @JsonProperty("rPercentCooldownMod")
    public Double getRPercentCooldownMod() {
        return rPercentCooldownMod;
    }

    /**
     * 
     * @param rPercentCooldownMod
     *     The rPercentCooldownMod
     */
    @JsonProperty("rPercentCooldownMod")
    public void setRPercentCooldownMod(Double rPercentCooldownMod) {
        this.rPercentCooldownMod = rPercentCooldownMod;
    }

    /**
     * 
     * @return
     *     The rPercentCooldownModPerLevel
     */
    @JsonProperty("rPercentCooldownModPerLevel")
    public Double getRPercentCooldownModPerLevel() {
        return rPercentCooldownModPerLevel;
    }

    /**
     * 
     * @param rPercentCooldownModPerLevel
     *     The rPercentCooldownModPerLevel
     */
    @JsonProperty("rPercentCooldownModPerLevel")
    public void setRPercentCooldownModPerLevel(Double rPercentCooldownModPerLevel) {
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentMagicPenetrationMod
     */
    @JsonProperty("rPercentMagicPenetrationMod")
    public Double getRPercentMagicPenetrationMod() {
        return rPercentMagicPenetrationMod;
    }

    /**
     * 
     * @param rPercentMagicPenetrationMod
     *     The rPercentMagicPenetrationMod
     */
    @JsonProperty("rPercentMagicPenetrationMod")
    public void setRPercentMagicPenetrationMod(Double rPercentMagicPenetrationMod) {
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
    }

    /**
     * 
     * @return
     *     The rPercentMagicPenetrationModPerLevel
     */
    @JsonProperty("rPercentMagicPenetrationModPerLevel")
    public Double getRPercentMagicPenetrationModPerLevel() {
        return rPercentMagicPenetrationModPerLevel;
    }

    /**
     * 
     * @param rPercentMagicPenetrationModPerLevel
     *     The rPercentMagicPenetrationModPerLevel
     */
    @JsonProperty("rPercentMagicPenetrationModPerLevel")
    public void setRPercentMagicPenetrationModPerLevel(Double rPercentMagicPenetrationModPerLevel) {
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentMovementSpeedModPerLevel
     */
    @JsonProperty("rPercentMovementSpeedModPerLevel")
    public Double getRPercentMovementSpeedModPerLevel() {
        return rPercentMovementSpeedModPerLevel;
    }

    /**
     * 
     * @param rPercentMovementSpeedModPerLevel
     *     The rPercentMovementSpeedModPerLevel
     */
    @JsonProperty("rPercentMovementSpeedModPerLevel")
    public void setRPercentMovementSpeedModPerLevel(Double rPercentMovementSpeedModPerLevel) {
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
    }

    /**
     * 
     * @return
     *     The rPercentTimeDeadMod
     */
    @JsonProperty("rPercentTimeDeadMod")
    public Double getRPercentTimeDeadMod() {
        return rPercentTimeDeadMod;
    }

    /**
     * 
     * @param rPercentTimeDeadMod
     *     The rPercentTimeDeadMod
     */
    @JsonProperty("rPercentTimeDeadMod")
    public void setRPercentTimeDeadMod(Double rPercentTimeDeadMod) {
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
    }

    /**
     * 
     * @return
     *     The rPercentTimeDeadModPerLevel
     */
    @JsonProperty("rPercentTimeDeadModPerLevel")
    public Double getRPercentTimeDeadModPerLevel() {
        return rPercentTimeDeadModPerLevel;
    }

    /**
     * 
     * @param rPercentTimeDeadModPerLevel
     *     The rPercentTimeDeadModPerLevel
     */
    @JsonProperty("rPercentTimeDeadModPerLevel")
    public void setRPercentTimeDeadModPerLevel(Double rPercentTimeDeadModPerLevel) {
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
    }

    /**
     * 
     * @return
     *     The flatArmorMod
     */
    @JsonProperty("FlatArmorMod")
    public Double getFlatArmorMod() {
        return flatArmorMod;
    }

    /**
     * 
     * @param flatArmorMod
     *     The FlatArmorMod
     */
    @JsonProperty("FlatArmorMod")
    public void setFlatArmorMod(Double flatArmorMod) {
        this.flatArmorMod = flatArmorMod;
    }

    /**
     * 
     * @return
     *     The flatAttackSpeedMod
     */
    @JsonProperty("FlatAttackSpeedMod")
    public Double getFlatAttackSpeedMod() {
        return flatAttackSpeedMod;
    }

    /**
     * 
     * @param flatAttackSpeedMod
     *     The FlatAttackSpeedMod
     */
    @JsonProperty("FlatAttackSpeedMod")
    public void setFlatAttackSpeedMod(Double flatAttackSpeedMod) {
        this.flatAttackSpeedMod = flatAttackSpeedMod;
    }

    /**
     * 
     * @return
     *     The flatBlockMod
     */
    @JsonProperty("FlatBlockMod")
    public Double getFlatBlockMod() {
        return flatBlockMod;
    }

    /**
     * 
     * @param flatBlockMod
     *     The FlatBlockMod
     */
    @JsonProperty("FlatBlockMod")
    public void setFlatBlockMod(Double flatBlockMod) {
        this.flatBlockMod = flatBlockMod;
    }

    /**
     * 
     * @return
     *     The flatCritChanceMod
     */
    @JsonProperty("FlatCritChanceMod")
    public Double getFlatCritChanceMod() {
        return flatCritChanceMod;
    }

    /**
     * 
     * @param flatCritChanceMod
     *     The FlatCritChanceMod
     */
    @JsonProperty("FlatCritChanceMod")
    public void setFlatCritChanceMod(Double flatCritChanceMod) {
        this.flatCritChanceMod = flatCritChanceMod;
    }

    /**
     * 
     * @return
     *     The flatCritDamageMod
     */
    @JsonProperty("FlatCritDamageMod")
    public Double getFlatCritDamageMod() {
        return flatCritDamageMod;
    }

    /**
     * 
     * @param flatCritDamageMod
     *     The FlatCritDamageMod
     */
    @JsonProperty("FlatCritDamageMod")
    public void setFlatCritDamageMod(Double flatCritDamageMod) {
        this.flatCritDamageMod = flatCritDamageMod;
    }

    /**
     * 
     * @return
     *     The flatEnergyRegenMod
     */
    @JsonProperty("FlatEnergyRegenMod")
    public Double getFlatEnergyRegenMod() {
        return flatEnergyRegenMod;
    }

    /**
     * 
     * @param flatEnergyRegenMod
     *     The FlatEnergyRegenMod
     */
    @JsonProperty("FlatEnergyRegenMod")
    public void setFlatEnergyRegenMod(Double flatEnergyRegenMod) {
        this.flatEnergyRegenMod = flatEnergyRegenMod;
    }

    /**
     * 
     * @return
     *     The flatEnergyPoolMod
     */
    @JsonProperty("FlatEnergyPoolMod")
    public Double getFlatEnergyPoolMod() {
        return flatEnergyPoolMod;
    }

    /**
     * 
     * @param flatEnergyPoolMod
     *     The FlatEnergyPoolMod
     */
    @JsonProperty("FlatEnergyPoolMod")
    public void setFlatEnergyPoolMod(Double flatEnergyPoolMod) {
        this.flatEnergyPoolMod = flatEnergyPoolMod;
    }

    /**
     * 
     * @return
     *     The flatEXPBonus
     */
    @JsonProperty("FlatEXPBonus")
    public Double getFlatEXPBonus() {
        return flatEXPBonus;
    }

    /**
     * 
     * @param flatEXPBonus
     *     The FlatEXPBonus
     */
    @JsonProperty("FlatEXPBonus")
    public void setFlatEXPBonus(Double flatEXPBonus) {
        this.flatEXPBonus = flatEXPBonus;
    }

    /**
     * 
     * @return
     *     The flatHPPoolMod
     */
    @JsonProperty("FlatHPPoolMod")
    public Double getFlatHPPoolMod() {
        return flatHPPoolMod;
    }

    /**
     * 
     * @param flatHPPoolMod
     *     The FlatHPPoolMod
     */
    @JsonProperty("FlatHPPoolMod")
    public void setFlatHPPoolMod(Double flatHPPoolMod) {
        this.flatHPPoolMod = flatHPPoolMod;
    }

    /**
     * 
     * @return
     *     The flatHPRegenMod
     */
    @JsonProperty("FlatHPRegenMod")
    public Double getFlatHPRegenMod() {
        return flatHPRegenMod;
    }

    /**
     * 
     * @param flatHPRegenMod
     *     The FlatHPRegenMod
     */
    @JsonProperty("FlatHPRegenMod")
    public void setFlatHPRegenMod(Double flatHPRegenMod) {
        this.flatHPRegenMod = flatHPRegenMod;
    }

    /**
     * 
     * @return
     *     The flatMPPoolMod
     */
    @JsonProperty("FlatMPPoolMod")
    public Double getFlatMPPoolMod() {
        return flatMPPoolMod;
    }

    /**
     * 
     * @param flatMPPoolMod
     *     The FlatMPPoolMod
     */
    @JsonProperty("FlatMPPoolMod")
    public void setFlatMPPoolMod(Double flatMPPoolMod) {
        this.flatMPPoolMod = flatMPPoolMod;
    }

    /**
     * 
     * @return
     *     The flatMPRegenMod
     */
    @JsonProperty("FlatMPRegenMod")
    public Double getFlatMPRegenMod() {
        return flatMPRegenMod;
    }

    /**
     * 
     * @param flatMPRegenMod
     *     The FlatMPRegenMod
     */
    @JsonProperty("FlatMPRegenMod")
    public void setFlatMPRegenMod(Double flatMPRegenMod) {
        this.flatMPRegenMod = flatMPRegenMod;
    }

    /**
     * 
     * @return
     *     The flatMagicDamageMod
     */
    @JsonProperty("FlatMagicDamageMod")
    public Double getFlatMagicDamageMod() {
        return flatMagicDamageMod;
    }

    /**
     * 
     * @param flatMagicDamageMod
     *     The FlatMagicDamageMod
     */
    @JsonProperty("FlatMagicDamageMod")
    public void setFlatMagicDamageMod(Double flatMagicDamageMod) {
        this.flatMagicDamageMod = flatMagicDamageMod;
    }

    /**
     * 
     * @return
     *     The flatMovementSpeedMod
     */
    @JsonProperty("FlatMovementSpeedMod")
    public Double getFlatMovementSpeedMod() {
        return flatMovementSpeedMod;
    }

    /**
     * 
     * @param flatMovementSpeedMod
     *     The FlatMovementSpeedMod
     */
    @JsonProperty("FlatMovementSpeedMod")
    public void setFlatMovementSpeedMod(Double flatMovementSpeedMod) {
        this.flatMovementSpeedMod = flatMovementSpeedMod;
    }

    /**
     * 
     * @return
     *     The flatPhysicalDamageMod
     */
    @JsonProperty("FlatPhysicalDamageMod")
    public Double getFlatPhysicalDamageMod() {
        return flatPhysicalDamageMod;
    }

    /**
     * 
     * @param flatPhysicalDamageMod
     *     The FlatPhysicalDamageMod
     */
    @JsonProperty("FlatPhysicalDamageMod")
    public void setFlatPhysicalDamageMod(Double flatPhysicalDamageMod) {
        this.flatPhysicalDamageMod = flatPhysicalDamageMod;
    }

    /**
     * 
     * @return
     *     The flatSpellBlockMod
     */
    @JsonProperty("FlatSpellBlockMod")
    public Double getFlatSpellBlockMod() {
        return flatSpellBlockMod;
    }

    /**
     * 
     * @param flatSpellBlockMod
     *     The FlatSpellBlockMod
     */
    @JsonProperty("FlatSpellBlockMod")
    public void setFlatSpellBlockMod(Double flatSpellBlockMod) {
        this.flatSpellBlockMod = flatSpellBlockMod;
    }

    /**
     * 
     * @return
     *     The percentArmorMod
     */
    @JsonProperty("PercentArmorMod")
    public Double getPercentArmorMod() {
        return percentArmorMod;
    }

    /**
     * 
     * @param percentArmorMod
     *     The PercentArmorMod
     */
    @JsonProperty("PercentArmorMod")
    public void setPercentArmorMod(Double percentArmorMod) {
        this.percentArmorMod = percentArmorMod;
    }

    /**
     * 
     * @return
     *     The percentAttackSpeedMod
     */
    @JsonProperty("PercentAttackSpeedMod")
    public Double getPercentAttackSpeedMod() {
        return percentAttackSpeedMod;
    }

    /**
     * 
     * @param percentAttackSpeedMod
     *     The PercentAttackSpeedMod
     */
    @JsonProperty("PercentAttackSpeedMod")
    public void setPercentAttackSpeedMod(Double percentAttackSpeedMod) {
        this.percentAttackSpeedMod = percentAttackSpeedMod;
    }

    /**
     * 
     * @return
     *     The percentBlockMod
     */
    @JsonProperty("PercentBlockMod")
    public Double getPercentBlockMod() {
        return percentBlockMod;
    }

    /**
     * 
     * @param percentBlockMod
     *     The PercentBlockMod
     */
    @JsonProperty("PercentBlockMod")
    public void setPercentBlockMod(Double percentBlockMod) {
        this.percentBlockMod = percentBlockMod;
    }

    /**
     * 
     * @return
     *     The percentCritChanceMod
     */
    @JsonProperty("PercentCritChanceMod")
    public Double getPercentCritChanceMod() {
        return percentCritChanceMod;
    }

    /**
     * 
     * @param percentCritChanceMod
     *     The PercentCritChanceMod
     */
    @JsonProperty("PercentCritChanceMod")
    public void setPercentCritChanceMod(Double percentCritChanceMod) {
        this.percentCritChanceMod = percentCritChanceMod;
    }

    /**
     * 
     * @return
     *     The percentCritDamageMod
     */
    @JsonProperty("PercentCritDamageMod")
    public Double getPercentCritDamageMod() {
        return percentCritDamageMod;
    }

    /**
     * 
     * @param percentCritDamageMod
     *     The PercentCritDamageMod
     */
    @JsonProperty("PercentCritDamageMod")
    public void setPercentCritDamageMod(Double percentCritDamageMod) {
        this.percentCritDamageMod = percentCritDamageMod;
    }

    /**
     * 
     * @return
     *     The percentDodgeMod
     */
    @JsonProperty("PercentDodgeMod")
    public Double getPercentDodgeMod() {
        return percentDodgeMod;
    }

    /**
     * 
     * @param percentDodgeMod
     *     The PercentDodgeMod
     */
    @JsonProperty("PercentDodgeMod")
    public void setPercentDodgeMod(Double percentDodgeMod) {
        this.percentDodgeMod = percentDodgeMod;
    }

    /**
     * 
     * @return
     *     The percentEXPBonus
     */
    @JsonProperty("PercentEXPBonus")
    public Double getPercentEXPBonus() {
        return percentEXPBonus;
    }

    /**
     * 
     * @param percentEXPBonus
     *     The PercentEXPBonus
     */
    @JsonProperty("PercentEXPBonus")
    public void setPercentEXPBonus(Double percentEXPBonus) {
        this.percentEXPBonus = percentEXPBonus;
    }

    /**
     * 
     * @return
     *     The percentHPPoolMod
     */
    @JsonProperty("PercentHPPoolMod")
    public Double getPercentHPPoolMod() {
        return percentHPPoolMod;
    }

    /**
     * 
     * @param percentHPPoolMod
     *     The PercentHPPoolMod
     */
    @JsonProperty("PercentHPPoolMod")
    public void setPercentHPPoolMod(Double percentHPPoolMod) {
        this.percentHPPoolMod = percentHPPoolMod;
    }

    /**
     * 
     * @return
     *     The percentHPRegenMod
     */
    @JsonProperty("PercentHPRegenMod")
    public Double getPercentHPRegenMod() {
        return percentHPRegenMod;
    }

    /**
     * 
     * @param percentHPRegenMod
     *     The PercentHPRegenMod
     */
    @JsonProperty("PercentHPRegenMod")
    public void setPercentHPRegenMod(Double percentHPRegenMod) {
        this.percentHPRegenMod = percentHPRegenMod;
    }

    /**
     * 
     * @return
     *     The percentMPPoolMod
     */
    @JsonProperty("PercentMPPoolMod")
    public Double getPercentMPPoolMod() {
        return percentMPPoolMod;
    }

    /**
     * 
     * @param percentMPPoolMod
     *     The PercentMPPoolMod
     */
    @JsonProperty("PercentMPPoolMod")
    public void setPercentMPPoolMod(Double percentMPPoolMod) {
        this.percentMPPoolMod = percentMPPoolMod;
    }

    /**
     * 
     * @return
     *     The percentMPRegenMod
     */
    @JsonProperty("PercentMPRegenMod")
    public Double getPercentMPRegenMod() {
        return percentMPRegenMod;
    }

    /**
     * 
     * @param percentMPRegenMod
     *     The PercentMPRegenMod
     */
    @JsonProperty("PercentMPRegenMod")
    public void setPercentMPRegenMod(Double percentMPRegenMod) {
        this.percentMPRegenMod = percentMPRegenMod;
    }

    /**
     * 
     * @return
     *     The percentMagicDamageMod
     */
    @JsonProperty("PercentMagicDamageMod")
    public Double getPercentMagicDamageMod() {
        return percentMagicDamageMod;
    }

    /**
     * 
     * @param percentMagicDamageMod
     *     The PercentMagicDamageMod
     */
    @JsonProperty("PercentMagicDamageMod")
    public void setPercentMagicDamageMod(Double percentMagicDamageMod) {
        this.percentMagicDamageMod = percentMagicDamageMod;
    }

    /**
     * 
     * @return
     *     The percentMovementSpeedMod
     */
    @JsonProperty("PercentMovementSpeedMod")
    public Double getPercentMovementSpeedMod() {
        return percentMovementSpeedMod;
    }

    /**
     * 
     * @param percentMovementSpeedMod
     *     The PercentMovementSpeedMod
     */
    @JsonProperty("PercentMovementSpeedMod")
    public void setPercentMovementSpeedMod(Double percentMovementSpeedMod) {
        this.percentMovementSpeedMod = percentMovementSpeedMod;
    }

    /**
     * 
     * @return
     *     The percentPhysicalDamageMod
     */
    @JsonProperty("PercentPhysicalDamageMod")
    public Double getPercentPhysicalDamageMod() {
        return percentPhysicalDamageMod;
    }

    /**
     * 
     * @param percentPhysicalDamageMod
     *     The PercentPhysicalDamageMod
     */
    @JsonProperty("PercentPhysicalDamageMod")
    public void setPercentPhysicalDamageMod(Double percentPhysicalDamageMod) {
        this.percentPhysicalDamageMod = percentPhysicalDamageMod;
    }

    /**
     * 
     * @return
     *     The percentSpellBlockMod
     */
    @JsonProperty("PercentSpellBlockMod")
    public Double getPercentSpellBlockMod() {
        return percentSpellBlockMod;
    }

    /**
     * 
     * @param percentSpellBlockMod
     *     The PercentSpellBlockMod
     */
    @JsonProperty("PercentSpellBlockMod")
    public void setPercentSpellBlockMod(Double percentSpellBlockMod) {
        this.percentSpellBlockMod = percentSpellBlockMod;
    }

    /**
     * 
     * @return
     *     The percentSpellVampMod
     */
    @JsonProperty("PercentSpellVampMod")
    public Double getPercentSpellVampMod() {
        return percentSpellVampMod;
    }

    /**
     * 
     * @param percentSpellVampMod
     *     The PercentSpellVampMod
     */
    @JsonProperty("PercentSpellVampMod")
    public void setPercentSpellVampMod(Double percentSpellVampMod) {
        this.percentSpellVampMod = percentSpellVampMod;
    }

    /**
     * 
     * @return
     *     The percentLifeStealMod
     */
    @JsonProperty("PercentLifeStealMod")
    public Double getPercentLifeStealMod() {
        return percentLifeStealMod;
    }

    /**
     * 
     * @param percentLifeStealMod
     *     The PercentLifeStealMod
     */
    @JsonProperty("PercentLifeStealMod")
    public void setPercentLifeStealMod(Double percentLifeStealMod) {
        this.percentLifeStealMod = percentLifeStealMod;
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
        dest.writeValue(this.rFlatArmorModPerLevel);
        dest.writeValue(this.rFlatArmorPenetrationMod);
        dest.writeValue(this.rFlatArmorPenetrationModPerLevel);
        dest.writeValue(this.rFlatCritChanceModPerLevel);
        dest.writeValue(this.rFlatCritDamageModPerLevel);
        dest.writeValue(this.rFlatEnergyRegenModPerLevel);
        dest.writeValue(this.rFlatDodgeMod);
        dest.writeValue(this.rFlatDodgeModPerLevel);
        dest.writeValue(this.rFlatGoldPer10Mod);
        dest.writeValue(this.rFlatHPModPerLevel);
        dest.writeValue(this.rFlatHPRegenModPerLevel);
        dest.writeValue(this.rFlatMPRegenModPerLevel);
        dest.writeValue(this.rFlatMagicDamageModPerLevel);
        dest.writeValue(this.rFlatMagicPenetrationMod);
        dest.writeValue(this.rFlatMagicPenetrationModPerLevel);
        dest.writeValue(this.rFlatMPModPerLevel);
        dest.writeValue(this.rFlatEnergyModPerLevel);
        dest.writeValue(this.rFlatMovementSpeedModPerLevel);
        dest.writeValue(this.rFlatPhysicalDamageModPerLevel);
        dest.writeValue(this.rFlatSpellBlockModPerLevel);
        dest.writeValue(this.rFlatTimeDeadMod);
        dest.writeValue(this.rFlatTimeDeadModPerLevel);
        dest.writeValue(this.rPercentArmorPenetrationMod);
        dest.writeValue(this.rPercentArmorPenetrationModPerLevel);
        dest.writeValue(this.rPercentAttackSpeedModPerLevel);
        dest.writeValue(this.rPercentCooldownMod);
        dest.writeValue(this.rPercentCooldownModPerLevel);
        dest.writeValue(this.rPercentMagicPenetrationMod);
        dest.writeValue(this.rPercentMagicPenetrationModPerLevel);
        dest.writeValue(this.rPercentMovementSpeedModPerLevel);
        dest.writeValue(this.rPercentTimeDeadMod);
        dest.writeValue(this.rPercentTimeDeadModPerLevel);
        dest.writeValue(this.flatArmorMod);
        dest.writeValue(this.flatAttackSpeedMod);
        dest.writeValue(this.flatBlockMod);
        dest.writeValue(this.flatCritChanceMod);
        dest.writeValue(this.flatCritDamageMod);
        dest.writeValue(this.flatEnergyRegenMod);
        dest.writeValue(this.flatEnergyPoolMod);
        dest.writeValue(this.flatEXPBonus);
        dest.writeValue(this.flatHPPoolMod);
        dest.writeValue(this.flatHPRegenMod);
        dest.writeValue(this.flatMPPoolMod);
        dest.writeValue(this.flatMPRegenMod);
        dest.writeValue(this.flatMagicDamageMod);
        dest.writeValue(this.flatMovementSpeedMod);
        dest.writeValue(this.flatPhysicalDamageMod);
        dest.writeValue(this.flatSpellBlockMod);
        dest.writeValue(this.percentArmorMod);
        dest.writeValue(this.percentAttackSpeedMod);
        dest.writeValue(this.percentBlockMod);
        dest.writeValue(this.percentCritChanceMod);
        dest.writeValue(this.percentCritDamageMod);
        dest.writeValue(this.percentDodgeMod);
        dest.writeValue(this.percentEXPBonus);
        dest.writeValue(this.percentHPPoolMod);
        dest.writeValue(this.percentHPRegenMod);
        dest.writeValue(this.percentMPPoolMod);
        dest.writeValue(this.percentMPRegenMod);
        dest.writeValue(this.percentMagicDamageMod);
        dest.writeValue(this.percentMovementSpeedMod);
        dest.writeValue(this.percentPhysicalDamageMod);
        dest.writeValue(this.percentSpellBlockMod);
        dest.writeValue(this.percentSpellVampMod);
        dest.writeValue(this.percentLifeStealMod);
        dest.writeValue(this.additionalProperties);
    }

    public Stats() {
    }

    protected Stats(Parcel in) {
        this.rFlatArmorModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatArmorPenetrationMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatArmorPenetrationModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatCritChanceModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatCritDamageModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatEnergyRegenModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatDodgeMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatDodgeModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatGoldPer10Mod = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatHPModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatHPRegenModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMPRegenModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMagicDamageModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMagicPenetrationMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMagicPenetrationModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMPModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatEnergyModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatMovementSpeedModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatPhysicalDamageModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatSpellBlockModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatTimeDeadMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rFlatTimeDeadModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentArmorPenetrationMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentArmorPenetrationModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentAttackSpeedModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentCooldownMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentCooldownModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentMagicPenetrationMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentMagicPenetrationModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentMovementSpeedModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentTimeDeadMod = (Double) in.readValue(Double.class.getClassLoader());
        this.rPercentTimeDeadModPerLevel = (Double) in.readValue(Double.class.getClassLoader());
        this.flatArmorMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatAttackSpeedMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatBlockMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatCritChanceMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatCritDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatEnergyRegenMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatEnergyPoolMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatEXPBonus = (Double) in.readValue(Double.class.getClassLoader());
        this.flatHPPoolMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatHPRegenMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatMPPoolMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatMPRegenMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatMagicDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatMovementSpeedMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatPhysicalDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.flatSpellBlockMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentArmorMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentAttackSpeedMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentBlockMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentCritChanceMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentCritDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentDodgeMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentEXPBonus = (Double) in.readValue(Double.class.getClassLoader());
        this.percentHPPoolMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentHPRegenMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentMPPoolMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentMPRegenMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentMagicDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentMovementSpeedMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentPhysicalDamageMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentSpellBlockMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentSpellVampMod = (Double) in.readValue(Double.class.getClassLoader());
        this.percentLifeStealMod = (Double) in.readValue(Double.class.getClassLoader());
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
