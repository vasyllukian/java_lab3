package org.fpm.di.example.myhierarchy;

import javax.inject.Inject;

public class Genre {
    private final Fantasy f;
    private final Comedy co;
    private final Crime cr;
    private final Horror h;
    @Inject
    public Genre(Fantasy F, Comedy CO, Crime CR, Horror H){
        this.f = F;
        this.co = CO;
        this.cr = CR;
        this.h = H;
    }
    public Fantasy getFantasy(){
        return f;
    }
    public Comedy getComedy(){
        return co;
    }
    public Crime getCrime(){
        return cr;
    }
    public Horror getHorror(){
        return h;
    }
}
