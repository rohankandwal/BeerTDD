
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Malt {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("amount")
    @Expose
    public Amount amount;

}
