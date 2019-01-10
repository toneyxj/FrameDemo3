package com.xj.framedemo3;

import android.app.IntentService;
import android.content.Intent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TestIntent extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntent(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nonnull Intent intent) {

    }
}
