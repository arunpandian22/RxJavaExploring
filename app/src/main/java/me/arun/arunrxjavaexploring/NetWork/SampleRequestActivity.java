package me.arun.arunrxjavaexploring.NetWork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toolbar;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RoomDB.models.response.nowPlaying.ModelNowPlayingMovie;
import me.arun.arunrxjavaexploring.RoomDB.models.response.nowPlaying.Result;
import me.arun.arunrxjavaexploring.RxJavaApplication;
import me.arun.arunrxjavaexploring.model.collection.CollectionListModel;
import me.arun.arunrxjavaexploring.model.placeList.PlaceListModel;
import me.arun.arunrxjavaexploring.request.InterfaceService;
import me.arun.arunrxjavaexploring.utils.CommonUtills;
import me.arun.arunrxjavaexploring.utils.Network_check;
import retrofit2.Retrofit;


public class SampleRequestActivity extends AppCompatActivity
{
    String TAG = "SampleRequestActivity";
    PublishProcessor<ModelNowPlayingMovie> ppMovieRes = PublishProcessor.create();
    PublishProcessor<Integer> ppPagination=PublishProcessor.create();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvMovieList)
    RecyclerView rvMovieList;
    MovieListAdapter movieListAdapter;
    Retrofit retrofit;

    List<Result> movieList=new ArrayList<>();
    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private LinearLayoutManager layoutManager;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    InterfaceService interfaceService;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_request);
        ButterKnife.bind(this);
        toolbar.setTitle("NetWork Request");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAbTitle));
        setActionBar(toolbar);
        retrofit = ((RxJavaApplication) getApplication()).retrofitClient;
        interfaceService = retrofit.create(InterfaceService.class);
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
       layoutManager=new LinearLayoutManager(this);
//        NetworkingApiClient.setBaseUrl("https://api.themoviedb.org/3/movie/");
        setPpMovieResObserver();
        movieListAdapter=new MovieListAdapter(this,movieList);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setAdapter(movieListAdapter);
//        setUpLoadMoreListener();
//

        compositeDisposable.add(
                getCollectionListModel()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ModelNowPlayingMovie>() {

                    @Override
                    public void onSuccess(ModelNowPlayingMovie modelNowPlayingMovie) {

                        Log.d(TAG, "onNext: " + modelNowPlayingMovie.getResults().get(0).getOriginalTitle());
                        movieList=modelNowPlayingMovie.getResults();
                        loading=false;
                        if (movieList!=null) {
//                    movieListAdapter.setListMovies(movieList);
                            movieListAdapter.addMovies(movieList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                }));


//        if (Network_check.isNetworkAvailable(this))
////        makeMovieListApiCall(0);
//        else
//        {
//           // show error
//        }
    }


//    public void makeMovieListApiCall(int page)
//    {
//        HashMap<String, String> hmParams = new HashMap<>();
//        hmParams.put("api_key", "8a03975d504c762ab63b6c5fa98e3c17");
//        RxNetworkRequest<ModelNowPlayingMovie> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder<ModelNowPlayingMovie>(this,"now_playing", ObservableType.FLOWABLE, RequestType.POST, ModelNowPlayingMovie.class).setQueryParams(hmParams).build();
//        rxNetworkRequest.makeRequest(null, ppMovieRes,"");
//    }

    public void setPpMovieResObserver()
    {
        ppMovieRes.onBackpressureBuffer().subscribe(new Subscriber<ModelNowPlayingMovie>()
        {
            @Override
            public void onSubscribe(Subscription s)
            {
                Log.d(TAG, "onSubscribe: ");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(ModelNowPlayingMovie modelNowPlayingMovie)
            {
                Log.d(TAG, "onNext: " + modelNowPlayingMovie.getResults().get(0).getOriginalTitle());
                movieList=modelNowPlayingMovie.getResults();
                loading=false;
                if (movieList!=null) {
//                    movieListAdapter.setListMovies(movieList);
                    movieListAdapter.addMovies(movieList);
                }
            }


            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getLocalizedMessage());
                loading=false;
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }




    private Single<ModelNowPlayingMovie> getCollectionListModel() {
        return interfaceService.makeMoviesApiCall("8a03975d504c762ab63b6c5fa98e3c17");
    }



    /**
     * setting listener to get callback for load more
     */
    private void setUpLoadMoreListener() {
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
//                    ppPagination.onNext(pageNumber);
//                    makeMovieListApiCall(pageNumber);
                    loading = true;
                }
            }
        });
    }



}
