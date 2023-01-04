package org.fpm.di.example;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration c) {
        DummyBinder b = new DummyBinder();
        c.configure(b);
        return new DummyContainer(b);
    }
}
