package com.khalej.Boutique.model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface apiinterface_home {

    @FormUrlEncoded
    @POST("montag/boutique/Boutique_login.php")
    Call<List<contact_userinfo>> getcontacts_login(@Field("phonee") String phone , @Field("passw") String password);
    @FormUrlEncoded
    @POST("montag/boutique/Boutique_Registraion.php")
    Call<ResponseBody> getcontacts_newaccount(@Field("name") String name, @Field("password") String password, @Field("address")String address,
                                              @Field("phone") String phone);

    @FormUrlEncoded
    @POST("montag/boutique/Boutique_Registraion_Tager.php")
    Call<ResponseBody> getcontacts_newaccount_tager(@Field("name") String name, @Field("address")String address,
                                              @Field("phone") String phone);

    @GET("montag/boutique/Boutique_annonce.php")
    Call<List<contact_annonce>> getcontacts_annonce();
    @GET("montag/boutique/Boutique_first_category.php")
    Call<List<contact_home>> getcontacts_first();
    @FormUrlEncoded
    @POST("montag/boutique/Boutique_second_category.php")
    Call<List<contact_second_home>> getcontacts(@Field("id") int id);
    @GET("montag/boutique/Boutique_offers.php")
    Call<List<contact_offers>> getcontacts_offers();

    @FormUrlEncoded
    @POST("montag/boutique/Boutique_second_category_Search.php")
    Call<List<contact_second_home>>  getcontacts_Search(@Field("name") String text);
    @FormUrlEncoded
    @POST("montag/boutique/Boutique_add_order.php")
    Call<ResponseBody> getcontacts_order(@Field("name") String name, @Field("address") String address,
                                         @Field("phone") String phone,
                                         @Field("details") String details,
                                         @Field("date")String date,
                                         @Field("price")double price,
                                         @Field("charge")String charge
    );
    @FormUrlEncoded
    @POST("montag/boutique/Boutique_Myorders.php")
    Call<List<contact_order>> get_all_neworders(@Field("name") String name,@Field("address") String address,@Field("phone") String phone);

    @FormUrlEncoded
    @POST("montag/boutique/Boutique_second_category_Images.php")
    Call<List<contact_annonce>> getcontacts_Image(@Field("id") int id);
    @FormUrlEncoded
    @POST("montag/boutique/Boutique_offer_Images.php")
    Call<List<contact_annonce>> getcontacts_Image_offer(@Field("id") int id);
}

