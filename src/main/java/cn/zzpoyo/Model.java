package cn.zzpoyo;

public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
