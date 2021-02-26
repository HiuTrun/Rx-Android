package hiutrun.example.rxjavacourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import hiutrun.example.rxjavacourse.models.Task;
import hiutrun.example.rxjavacourse.utils.DataSource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

// Observable and Observer

public class Lesson1 extends AppCompatActivity {

    private static final String TAG = Lesson1.class.getSimpleName();

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Throwable {
                        Log.d(TAG, "test: "+Thread.currentThread().getName());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: called");
            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG, "onNext: "+Thread.currentThread().getName());
                Log.d(TAG, "onNext: "+task.getDescription());
                text.setText(task.getDescription());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ",e );
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: called");
            }
        });

    }
}