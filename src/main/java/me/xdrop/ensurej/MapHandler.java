package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapHandler<K, V> extends Handler<MapHandler<K, V>> {

    private Map<K, V> map;

    public MapHandler(Map<K, V> map) {
        this.map = map;
    }

    class KeyHandler<T> extends Handler<KeyHandler<T>> {
        private List<Predicate<K>> keyPredicates;

        public KeyHandler(List<Predicate<K>> keyPredicates) {
            this.keyPredicates = keyPredicates;
        }

        public KeyHandler(Predicate<K> keyPredicate) {
            this.keyPredicates = new ArrayList<>();
            keyPredicates.add(keyPredicate);
        }
    }

    class ValueHandler<T> extends Handler<KeyHandler<T>> {
        private List<Predicate<T>> valuePredicates;

        public ValueHandler(List<Predicate<T>> keyPredicates) {
            this.valuePredicates = keyPredicates;
        }

        public ValueHandler(Predicate<T> keyPredicate) {
            this.valuePredicates = new ArrayList<>();
            valuePredicates.add(keyPredicate);
        }


    }



    public KeyHandler<K> keys(Predicate<K> keyCheck) {

        return new KeyHandler<K>(keyCheck);
    }

    public KeyHandler<K> keys(Predicate<K> ... keyChecks) {
        return new KeyHandler<K>(Arrays.asList(keyChecks));
    }


    public Predicate<Void> values(Predicate<V> valueChecks) {
        //valuePredicates.add(valueChecks);
        return null;
    }

    public Predicate<Void> values(Predicate<V> ... valueChecks) {
        //valuePredicates.addAll(Arrays.asList(valueChecks));
        return null;
    }

}
