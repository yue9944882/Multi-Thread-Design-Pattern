package com.kimmin.pattern.guard;

import java.util.concurrent.Callable;

/**
 * Created by kimmin on 4/27/16.
 */
public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;
    public GuardedAction(Predicate guard){
        this.guard = guard;
    }
}
