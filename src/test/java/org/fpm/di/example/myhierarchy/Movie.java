package org.fpm.di.example.myhierarchy;

import javax.inject.Inject;

public class Movie {
    private final LordOfTheRings lotr;
    private final AceVentura av;
    private final Scarface sf;
    private final It it;
    @Inject
    public Movie(LordOfTheRings LOTR, AceVentura AV, Scarface SF, It IT){
        this.lotr = LOTR;
        this.av = AV;
        this.sf = SF;
        this.it = IT;
    }
    public LordOfTheRings getLordOfTheRings(){
        return lotr;
    }
    public AceVentura getAceVentura(){
        return av;
    }
    public Scarface getScarface(){
        return sf;
    }
    public It getIt(){
        return it;
    }
}
