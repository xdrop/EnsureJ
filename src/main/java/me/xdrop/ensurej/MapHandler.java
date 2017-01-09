package me.xdrop.ensurej;

import java.util.Map;

public class MapHandler<K,V> extends Handler<MapHandler<K,V>> {

    private Map<K,V> map;

    public MapHandler(Map<K, V> map) {
        this.map = map;
    }



}
