
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
    "base",
    "total",
    "sell",
    "purchasable"
})
public class Gold implements Parcelable {

    @JsonProperty("base")
    private Integer base;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("sell")
    private Integer sell;
    @JsonProperty("purchasable")
    private Boolean purchasable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The base
     */
    @JsonProperty("base")
    public Integer getBase() {
        return base;
    }

    /**
     * 
     * @param base
     *     The base
     */
    @JsonProperty("base")
    public void setBase(Integer base) {
        this.base = base;
    }

    /**
     * 
     * @return
     *     The total
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The sell
     */
    @JsonProperty("sell")
    public Integer getSell() {
        return sell;
    }

    /**
     * 
     * @param sell
     *     The sell
     */
    @JsonProperty("sell")
    public void setSell(Integer sell) {
        this.sell = sell;
    }

    /**
     * 
     * @return
     *     The purchasable
     */
    @JsonProperty("purchasable")
    public Boolean getPurchasable() {
        return purchasable;
    }

    /**
     * 
     * @param purchasable
     *     The purchasable
     */
    @JsonProperty("purchasable")
    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
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
        dest.writeValue(this.base);
        dest.writeValue(this.total);
        dest.writeValue(this.sell);
        dest.writeValue(this.purchasable);
        dest.writeValue(this.additionalProperties);
    }

    public Gold() {
    }

    protected Gold(Parcel in) {
        this.base = (Integer) in.readValue(Integer.class.getClassLoader());
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sell = (Integer) in.readValue(Integer.class.getClassLoader());
        this.purchasable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public static final Parcelable.Creator<Gold> CREATOR = new Parcelable.Creator<Gold>() {
        @Override
        public Gold createFromParcel(Parcel source) {
            return new Gold(source);
        }

        @Override
        public Gold[] newArray(int size) {
            return new Gold[size];
        }
    };
}
