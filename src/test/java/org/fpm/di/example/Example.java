package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di.example.myhierarchy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }
    // тести за моєю ієрархією класів
    @Test
    public void firstTest(){
        Movie movie = container.getComponent(Movie.class);
        assertSame(movie.getScarface(), container.getComponent(Scarface.class));
        //вони однакові, тому що фільм Scarface є тільки один
        assertSame(movie.getScarface(), container.getComponent(Crime.class));
        //майже той самий тест, тільки тепер фільм Scarface задається через клас Crime, імплементацією якого є цей філь у моїй ієрархії класів
        assertNotSame(movie.getLordOfTheRings(), container.getComponent(LordOfTheRings.class));
        // вони не однакові, тому що фільмів серії Lord Of The Rings багато, вони не є сінглтоном
        assertNotSame(movie.getLordOfTheRings(), container.getComponent(Fantasy.class));
        //майже той самий тест, тільки тепер фільми серії LordOfTheRings задаються через клас Fantasy, імплементацією якого є ці фільми
    }
    @Test
    public void secondTest(){
        Horror horror = container.getComponent(Horror.class);
        Movie movie = container.getComponent(Movie.class);
        assertSame(horror.getIt(), movie.getIt());
        //фільм It (Воно) існує тільки один, тож є сінглтоном
    }
    @Test
    public void thirdTest(){
        Genre genre = container.getComponent(Genre.class);
        assertSame(genre.getComedy().getTheOffice(), container.getComponent(TheOffice.class));
        //вони однакові оскільки серіал Офіс існує тільки в одному екземплярі
        Movie movie = container.getComponent(Movie.class);
        assertNotSame(movie.getAceVentura(), container.getComponent(AceVentura.class));
        assertNotSame(movie.getAceVentura(), genre.getComedy().getAceVentura());
        //аналогічна ситуація як з Lord of the Rings, фільм Ace Ventura не є сінглтоном, бо існує декілька фільмів з серії
    }
    @Test
    public void fourthTest(){
        Series series = container.getComponent(Series.class);
        assertSame(series.getTheWalkingDead(), container.getComponent(TheWalkingDead.class));
        //однакові оскільки серіал The Walking Dead є сінглтоном
    }
    @Test
    public void fifthTest(){
        Genre genre = container.getComponent(Genre.class);
        assertNotSame(genre, container.getComponent(Genre.class));
        //жанр не є сінглтоном, їх може бути багато
        assertNotSame(genre.getComedy(), container.getComponent(Comedy.class));
        //комедій також існує багато, кожна комедія є різною
        assertSame(genre.getComedy().getTheOffice(), container.getComponent(TheOffice.class));
        //а ось серіал офіс є тільки один
    }
}
