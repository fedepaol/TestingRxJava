package com.whiterabbit.rxjavatesting;

import android.widget.TextView;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


@RunWith(RxJavaTestRunner.class)
@Config(constants = BuildConfig.class,
application = TestRobolectricApplication.class)
public class SubscriberTest {
    @Before
    public void setup() {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void testRubberChickenName() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        TextView chickenName = (TextView) activity.findViewById(R.id.rubber_chicken_name);
        assertEquals(chickenName.getText(), "fedepaol");
    }
}
