package com.khalej.Boutique.model;
import com.google.gson.annotations.SerializedName;


public class contact_offers {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;

    @SerializedName("image")
    String Img;


    @SerializedName("details")
    String description;
    @SerializedName("newprice")
    String price;
    @SerializedName("oldprice")
    String oldprice;

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
