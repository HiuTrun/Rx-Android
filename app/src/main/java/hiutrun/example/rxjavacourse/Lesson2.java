package hiutrun.example.rxjavacourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hiutrun.example.rxjavacourse.models.Task;
import hiutrun.example.rxjavacourse.utils.DataSource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

// Disposal

public class Lesson2 extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        // taskObservable.subscribe(new Observer) -> void

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull Task task) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        // taskObservable.subscribe(new Consumer) -> disposal
        disposable.add(taskObservable.subscribe(new Consumer<Task>() {
            @Override
            public void accept(Task task) throws Throwable {

            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}