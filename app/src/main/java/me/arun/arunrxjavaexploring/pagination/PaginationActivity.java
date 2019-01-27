package me.arun.arunrxjavaexploring.pagination;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import org.reactivestreams.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.User;
public class PaginationActivity extends AppCompatActivity
{
    public static final String TAG = PaginationActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishProcessor<Integer> paginationPublisher = PublishProcessor.create();
    private RVAdapter paginationRvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        paginationRvAdapter = new RVAdapter();
        recyclerView.setAdapter(paginationRvAdapter);
        makeMoreLoadListener();
        dataObserververForpageload();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    /**
     * to setup the scroll listener for load more
     */
    private void makeMoreLoadListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    paginationPublisher.onNext(pageNumber);
                    loading = true;
                }
            }
        });
    }


    private void dataObserververForpageload()
    {

        Disposable disposable = paginationPublisher
                .onBackpressureDrop()
                .concatMap(new Function<Integer, Publisher<List<User>>>()
                {
                    @Override
                    public Publisher<List<User>> apply(@NonNull Integer page) {
                        loading = true;
                        progressBar.setVisibility(View.VISIBLE);
                        return dataGenerate(page);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(@NonNull List<User> items) {
                        paginationRvAdapter.addItems(items);
                        paginationRvAdapter.notifyDataSetChanged();
                        loading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);

        paginationPublisher.onNext(pageNumber);

    }

    /**
     * data generated for Pagiantion
     */
    private Flowable<List<User>> dataGenerate(final int page)
    {
        return Flowable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Boolean, List<User>>()
                {
                    @Override
                    public List<User> apply(@NonNull Boolean value)
                    {
                        List<User> items = new ArrayList<>();
                        for (int i = 1; i <= 10; i++)
                        {
                            User user=new User();
                            user.firstname="name"+(page * 10 + i);
                            user.lastname="name"+(page * 10 + i);
                            items.add(user);
                        }
                        return items;
                    }
                });
    }
}
