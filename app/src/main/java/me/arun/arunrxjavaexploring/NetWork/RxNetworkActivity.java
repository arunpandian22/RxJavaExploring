package me.arun.arunrxjavaexploring.NetWork;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RxJavaApplication;
import me.arun.arunrxjavaexploring.model.collection.CollectionListModel;
import me.arun.arunrxjavaexploring.model.collection.Value;
import me.arun.arunrxjavaexploring.model.placeList.PlaceListModel;
import me.arun.arunrxjavaexploring.request.InterfaceService;
import me.arun.arunrxjavaexploring.utils.CommonUtills;
import retrofit2.Retrofit;

public class RxNetworkActivity extends AppCompatActivity {
    Retrofit retrofit, retrofit1;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String TAG = "RxNetworkActivity";
    InterfaceService interfaceService;
    InterfaceService interfaceService1;
    Context context;
    Single<CollectionListModel> collectionListModelSingle;
    @BindView(R.id.tvResponseError)
    TextView tvResponseError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_network);
        ButterKnife.bind(this);
        retrofit = ((RxJavaApplication) getApplication()).retrofitClient;
        retrofit1 = ((RxJavaApplication) getApplication()).retrofitClient1;
        interfaceService1 = retrofit1.create(InterfaceService.class);
        interfaceService = retrofit.create(InterfaceService.class);
        context = this;
        collectionListModelSingle = getCollectionListModel();


        compositeDisposable.add(
                getCollectionListModel()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<CollectionListModel>() {

                    @Override
                    public void onSuccess(CollectionListModel collectionListModel) {
                        Log.d(TAG, "onSuccess: " + collectionListModel.getCollections());
                        tvResponseError.setText("Network call sucess"+collectionListModel.getCollections());
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvResponseError.setText("Network call Failure"+e.getMessage());

                    }
                }));


//        https://github.com/arunpandian22/PagingLibrarySampleJava/archive/master.zip

//
//        Call<ResponseBody> call = interfaceService1.fetchCaptcha1("");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        // display the image data in a ImageView or save it
//                        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
////                        id.setImageBitmap(bmp);
//                        Log.d(TAG, "onResponse: Bitmap"+bmp);
//                    } else {
//                        // TODO
//                        Log.d(TAG, "onResponse: response"+response);
//
//                    }
//                } else {
//                    // TODO
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                // TODO
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//
//        compositeDisposable.add(interfaceService.makePlaceListApiCall(CommonUtills.getDeviceId(this),"1").subscribeWith(new DisposableSingleObserver<PlaceListModel>() {
//            @Override
//            public void onSuccess(PlaceListModel o) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        }));


//        flatMap();
    }


//    /**
//     * flatMap and filter Operators Example
//     */


//    public void flatMap()
//    {
//        getCollectionListModel().observeOn(Schedulers.io()).map(new Function<CollectionListModel, List<Value>>() {
//            @Override
//            public List<Value> apply(CollectionListModel collectionListModel) throws Exception {
//                return collectionListModel.getCollections().getValue();
//            }
//        }).flatMap(new Function<List<Value>, Single<PlaceListModel>>() {
//            @Override
//            public Single<PlaceListModel> apply(List<Value> values) throws Exception {
//                Log.d(TAG, "apply: "+values.size());
//                return getPlaceListModel(""+values.get(0).getId());
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PlaceListModel>() {
//
//            @Override
//            public void onSuccess(PlaceListModel o) {
//                Log.d(TAG, "onSuccess: "+o.getCollection_desc());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: "+e.getLocalizedMessage());
//            }
//        });
//    }


//    public void flatMap(){
//      getCollectionListModel().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<CollectionListModel, Single<PlaceListModel>>() {
//          @Override
//          public Single<PlaceListModel> apply(CollectionListModel collectionListModel) throws Exception
//          {
//              return getPlaceListModel(""+collectionListModel.getCollections().getValue().get(0).getId());
//          }
//      }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PlaceListModel>()
//      {
//          @Override
//          public void accept(PlaceListModel placeListModel) throws Exception {
//
//          }
//      });
//
//    }

    public void flatMap() {
        getCollectionListModel().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<CollectionListModel, ObservableSource<Value>>() {
            @Override
            public ObservableSource<Value> apply(CollectionListModel collectionListModel) throws Exception {
                return Observable.fromIterable(collectionListModel.getCollections().getValue());
            }
        });

    }


//        Observable<List<String>> numbers = Observable.from(getContactList())
//        .map(v -> v.getNumber())
//        .toList()

    private Single<CollectionListModel> getCollectionListModel() {
        return interfaceService.makeCollectionApiCall(CommonUtills.getDeviceId(this));
    }

    private Single<PlaceListModel> getPlaceListModel(String collectionId) {
        return interfaceService.makePlaceListApiCall(CommonUtills.getDeviceId(this), collectionId);
    }


}
