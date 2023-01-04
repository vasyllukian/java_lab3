package org.fpm.di.example.myhierarchy;

import javax.inject.Inject;

public class Comedy {
    private final AceVentura av;
    private final TheOffice to;
    @Inject
    public Comedy(AceVentura AV, TheOffice TO){
        this.av = AV;
        this.to = TO;
    }
    public AceVentura getAceVentura(){
        return av;
    }
    public TheOffice getTheOffice(){
        return to;
    }
}
