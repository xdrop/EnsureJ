package me.xdrop.ensurej;


import java.util.List;
import java.util.Map;

public class Ensure {

    public static IntegerHandler<ParamCheckFailedException> that(int a){
        IntegerHandler<ParamCheckFailedException> inst = new IntegerHandler<ParamCheckFailedException>(a);
        inst.setExceptionClass(ParamCheckFailedException.class);
        return inst;
    }

//
//    public static FloatingPointHandler<ParamCheckFailedException> that(double a){
//        FloatingPointHandler<ParamCheckFailedException> self = new FloatingPointHandler<ParamCheckFailedException>(a);
//        self.setExceptionClass(ParamCheckFailedException.class);
//        return self;
//    }
//
//
//    public static FloatingPointHandler<ParamCheckFailedException> that(float a){
//        FloatingPointHandler<ParamCheckFailedException> self = new FloatingPointHandler<ParamCheckFailedException>(a);
//        self.setExceptionClass(ParamCheckFailedException.class);
//        return self;
//    }
//
//
//    public static BoolHandler<ParamCheckFailedException> that(boolean a){
//        BoolHandler<ParamCheckFailedException> self = new BoolHandler<ParamCheckFailedException>(a);
//        self.setExceptionClass(ParamCheckFailedException.class);
//        return self;
//    }
//
//
//    public static <E> ListHandler<E, ParamCheckFailedException> that(List<E> a){
//        ListHandler<E, ParamCheckFailedException> self = new ListHandler<E, ParamCheckFailedException>(a);
//        self.setExceptionClass(ParamCheckFailedException.class);
//        return self;
//    }
//
//    public static <K,V> MapHandler<K,V, ParamCheckFailedException> that(Map<K,V> a){
//        MapHandler<K,V, ParamCheckFailedException> self = new MapHandler<K, V, ParamCheckFailedException>(a);
//        self.setExceptionClass(ParamCheckFailedException.class);
//        return self;
//    }





}
