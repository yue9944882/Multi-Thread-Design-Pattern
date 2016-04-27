package com.kimmin.pattern.guard;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kimmin on 4/27/16.
 */
public class ConditionVarBlocker implements Blocker {

    private final Lock lock;
    private final Condition condition;

    public ConditionVarBlocker(Lock lock){
        this.lock = lock;
        this.condition = this.lock.newCondition();
    }

    public ConditionVarBlocker(){
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        V result;
        try{
            final Predicate guard = guardedAction.guard;
            while(!guard.evaluate()){
                condition.await();
            }
            result = guardedAction.call();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try{
            if(stateOperation.call()){
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try{
            if(stateOperation.call()){
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void signal() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}
