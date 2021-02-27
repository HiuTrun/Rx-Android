package hiutrun.example.rxjavacourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import hiutrun.example.rxjavacourse.models.Task;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


// Interval() And Timer()
public class Lesson4 extends AppCompatActivity {
    private static final String TAG = Lesson4.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Interval
//        Observable<Long> integerObservable = Observable
//                .interval(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .takeWhile(new Predicate<Long>() {
//                    @Override
//                    public boolean test(Long aLong) throws Throwable {
//                        Log.d(TAG, "test: "+Thread.currentThread().getName());
//                        return aLong<=5;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//
//
//        integerObservable.subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                Log.d(TAG, "onNext: "+aLong);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable<Long> taskObservable = Observable
                .timer(5,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Long>() {
            Long time = 0L;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                time = System.currentTimeMillis();
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.d(TAG, "onNext: "+ (System.currentTimeMillis()-time)/1000);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}