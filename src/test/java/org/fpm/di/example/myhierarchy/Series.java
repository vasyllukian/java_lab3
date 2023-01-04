package org.fpm.di.example.myhierarchy;

import javax.inject.Inject;

public class Series {
    private final TheOffice to;
    private final TheWalkingDead twd;
    @Inject
    public Series(TheOffice TO, TheWalkingDead TWD){
        this.to = TO;
        this.twd = TWD;
    }
    public TheOffice getTheOffice(){
        return to;
    }
    public TheWalkingDead getTheWalkingDead(){
        return twd;
    }
}
