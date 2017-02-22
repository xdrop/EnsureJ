package me.xdrop.ensurej;

import java.util.*;

public class MapHandler<K, V> extends Handler<MapHandler<K, V>> {

    private Map<K, V> map;

    public MapHandler(Map<K, V> map) {
        this.map = map;
    }

    class KeyHandler<T> extends Handler<KeyHandler<T>> {
        private List<Predicate<K>> keyPredicates;
        private Set<K> keys;

        public KeyHandler(List<Predicate<K>> keyPredicates, Set<K> keys) {
            this.keyPredicates = keyPredicates;
            this.keys = keys;
        }

        public KeyHandler(Predicate<K> keyPredicate, Set<K> keys) {
            this.keys = keys;
            this.keyPredicates = new ArrayList<>();
            keyPredicates.add(keyPredicate);
        }

        public KeyHandler<T> add(Predicate<K> keyPredicate){
            keyPredicates.add(keyPredicate);
            return this;
        }

        public Chain<Void, MapHandler<K,V>> all(){
            return new Chain<>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    for(Predicate<K> p : keyPredicates){
                        for (K key : keys){
                            if(!p.eval(key)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }, null, MapHandler.this, "One or more key checks failed");
        }

        public Chain<Void, MapHandler<K,V>> any(){
            return new Chain<>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    boolean truth = true;
                    for(Predicate<K> p : keyPredicates){
                        for (K key : keys){
                            if(!p.eval(key)) {
                                truth = false;
                            } else {
                                return true;
                            }
                        }
                    }
                    return truth;
                }
            }, null, MapHandler.this, "One or more key checks failed");
        }

    }

    class ValueHandler<T> extends Handler<KeyHandler<T>> {
        private List<Predicate<T>> valuePredicates;
        private Collection<T> values;

        public ValueHandler(List<Predicate<T>> valuePredicates, Collection<T> values) {
            this.valuePredicates = valuePredicates;
            this.values = values;
        }

        public ValueHandler(Predicate<T> valuePredicate, Collection<T> values) {
            this.valuePredicates = new ArrayList<>();
            valuePredicates.add(valuePredicate);
            this.values = values;
        }

        public ValueHandler<T> add(Predicate<T> valuePredicate){
            valuePredicates.add(valuePredicate);
            return this;
        }

        public Chain<Void, MapHandler<K,V>> all(){
            return new Chain<>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    for(Predicate<T> p : valuePredicates){
                        for (T val : values){
                            if(!p.eval(val)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }, null, MapHandler.this, "One or more value checks failed");
        }

        public Chain<Void, MapHandler<K,V>> any(){
            return new Chain<>(new Predicate<Void>() {
                @Override
                public boolean eval(Void in) {
                    boolean truth = true;
                    for(Predicate<T> p : valuePredicates){
                        for (T value : values){
                            if(!p.eval(value)) {
                                truth = false;
                            } else{
                                return true;
                            }
                        }
                    }
                    return truth;
                }
            }, null, MapHandler.this, "One or more key checks failed");
        }

    }

    public KeyHandler<K> keys(Predicate<K> keyCheck) {
        return new KeyHandler<K>(keyCheck, map.keySet());
    }

    public KeyHandler<K> keys(Predicate<K> ... keyChecks) {
        return new KeyHandler<>(Arrays.asList(keyChecks), map.keySet());
    }

    public ValueHandler<V> values(Predicate<V> valueCheck) {
        return new ValueHandler<>(Arrays.asList(valueCheck), map.values());
    }

    public ValueHandler<V> values(Predicate<V> ... valueChecks) {
        return new ValueHandler<>(Arrays.asList(valueChecks), map.values());
    }

}
