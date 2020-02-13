package com.newchar.woolhelper.state;

import androidx.collection.SparseArrayCompat;

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
public abstract class BaseStateLayout<T> implements IStateLayout<T> {

    protected T host;
    /**
     * 当前处于那种 state
     */
    protected int state;
    protected SparseArrayCompat<StateLayout> layoutArrays = new SparseArrayCompat<>(4);

    @Override
    public void attach(T attach) {
        host = attach;
    }

    @Override
    public T getHost() {
        return host;
    }

    public int getState() {
        return state;
    }

    public void putLayout(int state, StateLayout stateLayout) {
        layoutArrays.put(state, stateLayout);
    }

    public StateLayout getLayout(int state) {
        return layoutArrays.get(state);
    }

}
