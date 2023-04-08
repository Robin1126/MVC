package de.tu_ilmenau.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class MyThreadLocal<T> {
    /**
     * 所有需要和当前线程绑定的数据要放到这个容器当中
     */
    private Map<Thread, T> map = new HashMap<>();

    // 存储的方法
    public void set(T obj) {
        map.put(Thread.currentThread(), obj);
    }
    // 取对象的方法
    public T get() {
        return map.get(Thread.currentThread());
    }
    // 删除的方法
    public void del() {
        map.remove(Thread.currentThread());
    }
}
