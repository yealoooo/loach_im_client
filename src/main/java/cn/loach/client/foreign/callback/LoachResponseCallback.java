package cn.loach.client.foreign.callback;

public abstract class LoachResponseCallback<T> {

    public abstract void onNext(T responseMessage);
}
