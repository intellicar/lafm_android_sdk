package com.intellicar.dummylafproject;

public class ReadData<T> {
    public T data;
    public int nextidx;

    public ReadData(T data, int nextidx) {
        this.data = data;
        this.nextidx = nextidx;
    }
}

