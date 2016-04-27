package com.kimmin.pattern.guard;

import java.util.concurrent.Callable;

/**
 * Created by kimmin on 4/27/16.
 */
public interface Blocker {

    /** This method will be blocked until the condition of Guarded-Action is true **/
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    /** This method will be invoked after executing 'stateOperation' **/
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws InterruptedException;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;

}
