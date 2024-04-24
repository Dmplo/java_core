package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HumanResourcesDepartment implements Iterable<BaseWorker> {
    private final List<BaseWorker> workers;
    public HumanResourcesDepartment() {
        workers = new ArrayList<>();
    }

    @Override
    public Iterator<BaseWorker> iterator() {

        Iterator<BaseWorker> it = new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < workers.size();
            }

            @Override
            public BaseWorker next() {
                return workers.get(index++);
            }

        };
        return it;

    }

    public List<BaseWorker> getWorkers() {
        return workers;
    }

    public void addWorkers(BaseWorker worker) {
       workers.add(worker);
    }

}
