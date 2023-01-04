package org.fpm.di.example.myhierarchy;

import javax.inject.Inject;

public class Horror {
    private final It it;
    private final TheWalkingDead twd;
    @Inject
    public Horror(It IT, TheWalkingDead TWD){
        this.it = IT;
        this.twd = TWD;
    }
    public It getIt(){
        return it;
    }
    public TheWalkingDead getTheWalkingDead(){
        return twd;
    }
}
