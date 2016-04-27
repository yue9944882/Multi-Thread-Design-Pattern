package com.kimmin.pattern.immutable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kimmin on 4/27/16.
 */
public class ImmutableObject <T> {

    public ImmutableObject(){
        /** Do initialization here **/
    }

    public ImmutableObject(List<T> list){
        this.list = list;
    }

    private void setInstance(ImmutableObject immutableObject){
        /** Can only be invoked by Manipulator **/
        this.instance = immutableObject;
    }

    private ImmutableObject instance = new ImmutableObject();
    public ImmutableObjectManipulator<T> manipulator = new ImmutableObjectManipulator<T>();

    /** Data CRUD implementation **/
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private List<T> list = new ArrayList<T>();

    /** Current Modification Exception will never will threw here **/
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    public class ImmutableObjectManipulator<T> {
        public void changeStatusTo(ImmutableObject<T> oldInstance, List<T> list){
            ImmutableObject<T> newObject = new ImmutableObject<T>(list);
            oldInstance.setInstance(newObject);
        }
    }

}
