package co.codesharp.jwampsharp.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampIdMapper<T> {
    private final WampIdGenerator generator = new WampIdGenerator();

    private final ConcurrentHashMap<Long, T> idToValue = new ConcurrentHashMap<Long, T>();

    public long add(T value) {
        boolean added = false;
        long currentId = 0;

        while (!added) {
            currentId = generator.generate();

            T currentValue =
                    idToValue.putIfAbsent(currentId, value);

            added = (currentValue == null);
        }

        return currentId;
    }

    public T getOrDefault(long key, T defaultValue) {
        return idToValue.getOrDefault(key, defaultValue);
    }

    public T remove(long key) {
        return idToValue.remove(key);
    }
}