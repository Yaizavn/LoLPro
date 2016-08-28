
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
    "css",
    "dd",
    "l",
    "n",
    "profileiconmax",
    "v",
    "lg",
    "cdn"
})
public class BaseRealm {

    @JsonProperty("css")
    private String css;
    @JsonProperty("dd")
    private String dd;
    @JsonProperty("l")
    private String l;
    @JsonProperty("n")
    private N n;
    @JsonProperty("profileiconmax")
    private Integer profileiconmax;
    @JsonProperty("v")
    private String v;
    @JsonProperty("lg")
    private String lg;
    @JsonProperty("cdn")
    private String cdn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The css
     */
    @JsonProperty("css")
    public String getCss() {
        return css;
    }

    /**
     * 
     * @param css
     *     The css
     */
    @JsonProperty("css")
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * 
     * @return
     *     The dd
     */
    @JsonProperty("dd")
    public String getDd() {
        return dd;
    }

    /**
     * 
     * @param dd
     *     The dd
     */
    @JsonProperty("dd")
    public void setDd(String dd) {
        this.dd = dd;
    }

    /**
     * 
     * @return
     *     The l
     */
    @JsonProperty("l")
    public String getL() {
        return l;
    }

    /**
     * 
     * @param l
     *     The l
     */
    @JsonProperty("l")
    public void setL(String l) {
        this.l = l;
    }

    /**
     * 
     * @return
     *     The n
     */
    @JsonProperty("n")
    public N getN() {
        return n;
    }

    /**
     * 
     * @param n
     *     The n
     */
    @JsonProperty("n")
    public void setN(N n) {
        this.n = n;
    }

    /**
     * 
     * @return
     *     The profileiconmax
     */
    @JsonProperty("profileiconmax")
    public Integer getProfileiconmax() {
        return profileiconmax;
    }

    /**
     * 
     * @param profileiconmax
     *     The profileiconmax
     */
    @JsonProperty("profileiconmax")
    public void setProfileiconmax(Integer profileiconmax) {
        this.profileiconmax = profileiconmax;
    }

    /**
     * 
     * @return
     *     The v
     */
    @JsonProperty("v")
    public String getV() {
        return v;
    }

    /**
     * 
     * @param v
     *     The v
     */
    @JsonProperty("v")
    public void setV(String v) {
        this.v = v;
    }

    /**
     * 
     * @return
     *     The lg
     */
    @JsonProperty("lg")
    public String getLg() {
        return lg;
    }

    /**
     * 
     * @param lg
     *     The lg
     */
    @JsonProperty("lg")
    public void setLg(String lg) {
        this.lg = lg;
    }

    /**
     * 
     * @return
     *     The cdn
     */
    @JsonProperty("cdn")
    public String getCdn() {
        return cdn;
    }

    /**
     * 
     * @param cdn
     *     The cdn
     */
    @JsonProperty("cdn")
    public void setCdn(String cdn) {
        this.cdn = cdn;
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
