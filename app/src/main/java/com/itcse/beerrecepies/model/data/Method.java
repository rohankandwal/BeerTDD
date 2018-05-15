
package com.itcse.beerrecepies.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Method {

    @SerializedName("mash_temp")
    @Expose
    public List<Mash_temp> mash_temp = null;
    @SerializedName("fermentation")
    @Expose
    public Fermentation fermentation;
    @SerializedName("twist")
    @Expose
    public Object twist;

}
