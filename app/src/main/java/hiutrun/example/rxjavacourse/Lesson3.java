package hiutrun.example.rxjavacourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import hiutrun.example.rxjavacourse.models.Task;
import hiutrun.example.rxjavacourse.utils.DataSource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Lesson3 extends AppCompatActivity {

    private static final String TAG = Lesson3.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task task = new Task("Tidy my room",true,3);

        // Operator Create() called with single object task


//        Creating an Observable from a single object
//        1. Instantiate the object to become an Observable.
//        2. Create the Observable.
//        3. Subscribe to the Observable and get the emitted object.
//        Observable<Task> singleObservable = Observable
//                .create(new ObservableOnSubscribe<Task>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Throwable {
//                        if(!emitter.isDisposed()){
//                            emitter.onNext(task);
//                            emitter.onComplete();
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        singleObservable.subscribe(new Observer<Task>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Task task) {
//                Log.d(TAG, "onNext: "+task.getDescription());
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


//        Creating an Observable from a list of objects
//        1.Create the Observable.
//        2.Inside the subscribe method iterate through the list of tasks and call onNext(task).
//        3.Once the loop is complete, call the onComplete() method.
//        4.Subscribe to the Observable and get the emitted objects.
//        Observable<Task> taskListObservable = Observable
//                .create(new ObservableOnSubscribe<Task>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Throwable {
//                        for (Task task : DataSource.createTaskList()) {
//                            if (!emitter.isDisposed()) {
//                                emitter.onNext(task);
//                            }
//                        }
//                        if(!emitter.isDisposed()) {
//                            emitter.onComplete();
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        taskListObservable.subscribe(new Observer<Task>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Task task) {
//                Log.d(TAG, "onNext: "+task.getDescription());
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


        // Just()
        // MAXIMUM = 10 Object

        Observable.just("first", "second", "third", "fourth", "fifth", "sixth",
                "seventh", "eighth", "ninth", "tenth")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext: "+s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ",e );
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: done");
                    }
                });
        // Range() and Repeat()
    }
}