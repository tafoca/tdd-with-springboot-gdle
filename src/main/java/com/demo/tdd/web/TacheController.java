package com.demo.tdd.web;

import com.demo.tdd.domain.Tache;
import com.demo.tdd.service.TacheNotFoundException;
import com.demo.tdd.service.TacheService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TacheController {

    private TacheService service;

    public TacheController(TacheService service) {
        this.service = service;
    }

    @GetMapping("/Taches/{name}")
    public Tache getTache(@PathVariable String name) {
        return service.getTacheDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void TacheNotFoundHandler(TacheNotFoundException ex) {}

}
