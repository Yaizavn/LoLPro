
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
    "full",
    "sprite",
    "group",
    "x",
    "y",
    "w",
    "h"
})
public class Image implements Parcelable {

    @JsonProperty("full")
    private String full;
    @JsonProperty("sprite")
    private String sprite;
    @JsonProperty("group")
    private String group;
    @JsonProperty("x")
    private Integer x;
    @JsonProperty("y")
    private Integer y;
    @JsonProperty("w")
    private Integer w;
    @JsonProperty("h")
    private Integer h;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The full
     */
    @JsonProperty("full")
    public String getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    @JsonProperty("full")
    public void setFull(String full) {
        this.full = full;
    }

    /**
     * 
     * @return
     *     The sprite
     */
    @JsonProperty("sprite")
    public String getSprite() {
        return sprite;
    }

    /**
     * 
     * @param sprite
     *     The sprite
     */
    @JsonProperty("sprite")
    public void setSprite(String sprite) {
        this.sprite = sprite;
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
     *     The x
     */
    @JsonProperty("x")
    public Integer getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    @JsonProperty("x")
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    @JsonProperty("y")
    public Integer getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    @JsonProperty("y")
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * 
     * @return
     *     The w
     */
    @JsonProperty("w")
    public Integer getW() {
        return w;
    }

    /**
     * 
     * @param w
     *     The w
     */
    @JsonProperty("w")
    public void setW(Integer w) {
        this.w = w;
    }

    /**
     * 
     * @return
     *     The h
     */
    @JsonProperty("h")
    public Integer getH() {
        return h;
    }

    /**
     * 
     * @param h
     *     The h
     */
    @JsonProperty("h")
    public void setH(Integer h) {
        this.h = h;
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
        dest.writeString(this.full);
        dest.writeString(this.sprite);
        dest.writeString(this.group);
        dest.writeValue(this.x);
        dest.writeValue(this.y);
        dest.writeValue(this.w);
        dest.writeValue(this.h);
        dest.writeValue(this.additionalProperties);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.full = in.readString();
        this.sprite = in.readString();
        this.group = in.readString();
        this.x = (Integer) in.readValue(Integer.class.getClassLoader());
        this.y = (Integer) in.readValue(Integer.class.getClassLoader());
        this.w = (Integer) in.readValue(Integer.class.getClassLoader());
        this.h = (Integer) in.readValue(Integer.class.getClassLoader());
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
