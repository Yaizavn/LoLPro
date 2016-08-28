
package com.lol.lolpro.app.json.Campeones;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class Data {
	
	private Map<String, Champion> mCampeones = new HashMap<String, Champion>();
    
    @JsonAnyGetter
    public Map<String, Champion> getMCampeones() {
        return this.mCampeones;
    }

    @JsonAnySetter
    public void setMCampeones(String name, Champion value) {
        this.mCampeones.put(name, value);
    }

}
