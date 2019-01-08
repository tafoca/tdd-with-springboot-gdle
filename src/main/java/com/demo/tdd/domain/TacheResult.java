package com.demo.tdd.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
//use of "<Lombok>" to reduce boilerplate code.
@Getter @Setter @NoArgsConstructor
public class TacheResult {
    private List<Tache> taches;

    public List<Tache> getTaches() {
        return  taches;
    }
}
