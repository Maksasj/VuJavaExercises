package com.radioboos.poke_pedia.filter;

public abstract class BaseFilter<T> {
    BaseFilter<T> prev;

    public BaseFilter(BaseFilter<T> prev) {
        this.prev = prev;
    }

    public abstract boolean filter(T pokemon);
}
