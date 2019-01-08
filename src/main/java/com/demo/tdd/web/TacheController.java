package com.demo.tdd.web;

import com.demo.tdd.domain.Tache;
import com.demo.tdd.service.TacheNotFoundException;
import com.demo.tdd.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class TacheController {
    @Autowired
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

    @RequestMapping(value = "/async/{title}", method = RequestMethod.GET)
    @Async
    public CompletableFuture<List<Tache>> searchASync(@PathVariable(name = "title") String title) {

        return CompletableFuture.completedFuture(service.getTacheByTitle(title));

    }

    @RequestMapping(value = "/sync/{title}", method = RequestMethod.GET)
    public @ResponseBody CompletableFuture<List<Tache>> searchSync(@PathVariable(name = "title") String title) {

        return (CompletableFuture<List<Tache>>) service.getTacheByTitle(title);

    }

}
