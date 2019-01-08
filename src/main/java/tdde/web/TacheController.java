package tdde.web;

import tdde.domain.Tache;
import tdde.service.TacheNotFoundException;
import tdde.service.TacheService;
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


    @GetMapping("/Taches/delete/{name}")
    public Tache deleteTache(@PathVariable String name) {
        return service.delete(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void TacheNotFoundHandler(TacheNotFoundException ex) {}

}
