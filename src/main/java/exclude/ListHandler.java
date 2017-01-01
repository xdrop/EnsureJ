package exclude;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

import java.util.List;

public class ListHandler<E, T extends ParamCheckFailedException> extends Handler<T> {

    private List<E> lst;

    public ListHandler(List<E> lst) {
        this.lst = lst;
    }
}
