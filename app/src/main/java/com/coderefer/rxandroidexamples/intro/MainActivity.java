package com.coderefer.rxandroidexamples.intro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.coderefer.rxandroidexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Observable<Integer> mObservable;
    private Observer<Integer> mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    code to initialize Observable
    private void initializeObservable() {
        mObservable = Observable.create(e -> {
            for(int i=1; i<=5;i++){
                e.onNext(i);
            }
            e.onComplete();
        });
    }

//    Code to initialize Observer
    private void initializeObserver() {
        mObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(MainActivity.this, "onSubscribe called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Integer integer) {
                Toast.makeText(MainActivity.this, "onNext called: " + integer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete called", Toast.LENGTH_SHORT).show();
            }
        };
    }

//    method which will be called on button click
    public void performAction(View view) {
        initializeObservable();
        initializeObserver();
//        subscribing observer to observable
        mObservable.subscribe(mObserver);
    }
}
