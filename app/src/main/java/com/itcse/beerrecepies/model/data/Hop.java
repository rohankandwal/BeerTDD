
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hop {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("amount")
    @Expose
    public Amount amount;
    @SerializedName("add")
    @Expose
    public String add;
    @SerializedName("attribute")
    @Expose
    public String attribute;

}
