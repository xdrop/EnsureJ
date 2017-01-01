package exclude;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

import java.util.Map;

public class MapHandler<K, V, T extends ParamCheckFailedException> extends Handler<T> {

    private Map<K,V> map;

    public MapHandler(Map<K, V> map) {
        this.map = map;
    }
}
