package com.app.blood_donation.home.remote;


import com.app.blood_donation.Constant;
import com.app.blood_donation.home.model.ImageUpload;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

//    @POST("retrofit/POST/readcontacts.php")
//    Call<List<Product>> getContacts();
//
//    @FormUrlEncoded
//    @POST("retrofit/POST/addcontact.php")
//    public Call<Product> insertUser(
//            @Field("name") String name,
//            @Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("retrofit/POST/editcontact.php")
//    public Call<Product> editUser(
//            @Field("id") String id,
//            @Field("name") String name,
//            @Field("email") String email);
//
//
//    @FormUrlEncoded
//    @POST("retrofit/POST/deletecontact.php")
//    Call<Product> deleteUser(
//            @Field("id") int id
//    );
//
//
//
//
//       //for live data search
//    @GET("android/product.php")
//    Call<List<Product>> getProduct(
//            @Query("item_type") String item_type,
//            @Query("key") String keyword,
//            @Query("cell") String cell
//    );
//
//    //for live data search
//    @GET("android/all_products.php")
//    Call<List<Product>> getAllProduct(
//            @Query("item_type") String item_type,
//            @Query("key") String keyword,
//            @Query("cell") String cell
//    );
//
//
//
//    /* #notes
//    -you can use only one annotation par request
//       -You can not mix @part or @Field annotation
//       -If we use Field in multi part it send whole string with "  "
//       -So you use Request body for that to remove  " "
//
//     */

    //for upload image and info
    @Multipart
    @POST("android/profile_image_upload.php")
    Call<ImageUpload> uploadFile(@Part MultipartBody.Part file,
                                 @Part(Constant.KEY_FILE) RequestBody name,
                                 @Part(Constant.KEY_CELL) RequestBody cell);
}