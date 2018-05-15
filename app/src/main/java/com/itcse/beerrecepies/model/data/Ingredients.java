
package com.itcse.beerrecepies.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients {

    @SerializedName("malt")
    @Expose
    public List<Malt> malt = null;
    @SerializedName("hops")
    @Expose
    public List<Hop> hops = null;
    @SerializedName("yeast")
    @Expose
    public String yeast;

}
