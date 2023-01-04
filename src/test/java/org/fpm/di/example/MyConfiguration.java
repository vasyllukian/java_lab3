package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di.example.myhierarchy.*;

public class MyConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());

        binder.bind(Genre.class);
        binder.bind(Movie.class);
        binder.bind(Series.class);

        binder.bind(Fantasy.class, LordOfTheRings.class);
        binder.bind(Crime.class, Scarface.class);
    }
}
