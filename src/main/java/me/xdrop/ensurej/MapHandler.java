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

        public KeyHandler<T> add(Predicate<K> keyPredicate){
            keyPredicates.add(keyPredicate);
            return this;
        }

        public Chain<Void, MapHandler<K,V>> all(){
            return new Chain<Void, MapHandler<K, V>>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    return false;
                }
            }, null, MapHandler.this, "One or more key checks failed");
        }

    }

    class ValueHandler<T> extends Handler<KeyHandler<T>> {
        private List<Predicate<T>> valuePredicates;

        public ValueHandler(List<Predicate<T>> valuePredicates) {
            this.valuePredicates = valuePredicates;
        }

        public ValueHandler(Predicate<T> valuePredicate) {
            this.valuePredicates = new ArrayList<>();
            valuePredicates.add(valuePredicate);
        }

        public ValueHandler<T> add(Predicate<T> valuePredicate){
            valuePredicates.add(valuePredicate);
            return this;
        }

        public Chain<Void, MapHandler<K,V>> all(){
            return new Chain<Void, MapHandler<K, V>>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    return false;
                }
            }, null, MapHandler.this, "One or more value checks failed");
        }

    }

    public KeyHandler<K> keys(Predicate<K> keyCheck) {
        return new KeyHandler<K>(keyCheck);
    }

    public KeyHandler<K> keys(Predicate<K> ... keyChecks) {
        return new KeyHandler<>(Arrays.asList(keyChecks));
    }

    public ValueHandler<V> values(Predicate<V> valueCheck) {
        return new ValueHandler<>(Arrays.asList(valueCheck));
    }

    public ValueHandler<V> values(Predicate<V> ... valueChecks) {
        return new ValueHandler<>(Arrays.asList(valueChecks));
    }

}
