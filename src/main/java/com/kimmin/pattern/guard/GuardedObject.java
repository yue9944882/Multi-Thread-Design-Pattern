package com.kimmin.pattern.guard;

/**
 * Created by kimmin on 4/27/16.
 */
public class GuardedObject {

    private boolean flag = false;
    private final Blocker blocker = new ConditionVarBlocker();
    private final Predicate isAllowed = new Predicate() {
        public boolean evaluate() {
            return flag;
        }
    };

    public void guardedMethod() throws Exception {
        /** This method is guarded by Guarded-Suspension Pattern **/
        System.out.println("Guarded Method Invocation!");
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(isAllowed) {
            public Void call() throws Exception {
                System.out.println("WE ARE DOING STH REAL COOL!");
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }



}
