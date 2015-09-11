package com.whiterabbit.rxjavatesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.whiterabbit.rxjavatesting.pojos.RubberChicken;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    ObservablesFactory mObservables;

    private Subscription mSubscription;
    private TextView mTextView;
    private Observable<RubberChicken> mObservable;

    private void subscribe() {
       mSubscription = mObservable.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<RubberChicken>() {
            @Override
            public void call(RubberChicken rubberChicken) {
                mTextView.setText(rubberChicken.getName());
            }
        });
    }

    private void unsubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getComponent().inject(this);

        mTextView = (TextView) findViewById(R.id.rubber_chicken_name);
        mObservable = mObservables.getObservable();

        subscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }
}
