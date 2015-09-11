package com.whiterabbit.rxjavatesting;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ObservablesTest {
    private TestObservables obsFactory;

    @Before
    public void setUp() {
        obsFactory = new TestObservables();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWithBlocking() {
        RubberChicken chicken = obsFactory.getObservable().toBlocking().first();
        assertNotNull(chicken);
        assertEquals(chicken.getName(), "fedepaol");
    }

    @Test
    public void testWithSubscriber() {
        Observable<RubberChicken> obs = obsFactory.getObservable();
        TestSubscriber<RubberChicken> testSubscriber = new TestSubscriber<>();
        obs.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<RubberChicken> chickens = testSubscriber.getOnNextEvents();
        assertEquals(chickens.size(), 2);
        Assert.assertEquals(chickens.get(0).getName(), "fedepaol");
        Assert.assertEquals(chickens.get(1).getName(), "arubberchicken");
    }

    @Test
    public void testComposition() {
        Observable<RubberChicken> o = obsFactory.getObservable();
        Observable<List<RubberChicken>> obs = obsFactory.getTransformedObservable(o);
        TestSubscriber<List<RubberChicken>> testSubscriber = new TestSubscriber<>();
        obs.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<List<RubberChicken>> listsOfChickens = testSubscriber.getOnNextEvents();
        Assert.assertEquals("Only one list back", listsOfChickens.size(), 1);
        List<RubberChicken> emittedList = listsOfChickens.get(0);
        Assert.assertEquals("A two elems list back", emittedList.size(), 2);
        Assert.assertEquals("The name is uppercased", emittedList.get(0).getName(), "FEDEPAOL");
    }
}
