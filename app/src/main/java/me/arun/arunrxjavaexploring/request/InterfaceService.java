package me.arun.arunrxjavaexploring.request;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import me.arun.arunrxjavaexploring.model.collection.CollectionListModel;
import me.arun.arunrxjavaexploring.model.placeList.PlaceListModel;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface InterfaceService
{
//    @Headers({"Accept: application/json"})
//    @POST("/graphql")
//    Call<ResponseBody> makeGraphQlApiCall(@Header("deviceid") String deviceid, @Body RequestBody requestBody);

    @Headers({"Accept: application/json"})
    @POST("/graphql")
    Single<ResponseBody> makeGraphQlApiCall(@Header("deviceid") String deviceid, @Body RequestBody requestBody);


    @Headers({"Accept: application/json"})
    @GET("collections")
    Single<CollectionListModel> makeCollectionApiCall(@Header("deviceid") String deviceid);

    @Headers({"Accept: application/json"})
    @GET("lists")
    Single<PlaceListModel> makePlaceListApiCall(@Header("deviceid") String deviceid, @Query("collectionId") String collectionId);
//    // Register new user
//    @FormUrlEncoded
//    @POST("notes/user/register")
//    Single<User> register(@Field("device_id") String deviceId);

    // Create note
    @FormUrlEncoded
    @POST("notes/new")
    Single<ContactsContract.CommonDataKinds.Note> createNote(@Field("note") String note);

    // Fetch all notes
    @GET("notes/all")
    Single<List<ContactsContract.CommonDataKinds.Note>> fetchAllNotes();



    // Update single note
    @FormUrlEncoded
    @PUT("notes/{id}")
    Completable updateNote(@Path("id") int noteId, @Field("note") String note);

    // Delete note
    @DELETE("notes/{id}")
    Completable deleteNote(@Path("id") int noteId);


    @GET
    @Streaming
    Single<Response<Bitmap>> fetchCaptcha(@Url String url);

    @GET
    Call<ResponseBody> fetchCaptcha1(@Url String url);


    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);



    // Retrofit 2 GET request for rxjava
    @Streaming
    @GET
    Observable<Response<ResponseBody>> downloadFileByUrlRx(@Url String fileUrl);


}
