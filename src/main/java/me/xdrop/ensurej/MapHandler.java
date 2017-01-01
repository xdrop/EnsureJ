package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

import java.util.Map;

public class MapHandler<K, V, T extends ParamCheckFailedException> extends Handler<T, MapHandler<K, V, T>> {

    private Map<K,V> map;

    MapHandler(Map<K, V> map) {
        this.map = map;
    }

}
