package br.com.visualnuts.VisualNuts.controller;

import br.com.visualnuts.VisualNuts.dto.World;
import br.com.visualnuts.VisualNuts.service.VisualNutsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VisualNutsController {

    @Autowired
    private VisualNutsService visualNutsService;

    @GetMapping(path = "/test")
    @Cacheable("test")
    public ResponseEntity teste (@RequestHeader(value = "authorization") String token){
        visualNutsService.authenticate(token);
        return ResponseEntity.status(HttpStatus.OK).body("All OK");
    }

    @GetMapping(path = "/E1")
    @Cacheable("e1")
    public ResponseEntity Exercise1 (@RequestHeader(value = "authorization") String token){
        visualNutsService.authenticate(token);
        String[] E1 = visualNutsService.exercise1();
        return ResponseEntity.status(HttpStatus.OK).body(E1);
    }

    @PostMapping(path = "/E2")
    @Cacheable("e2")
    public ResponseEntity Exercise2 (@RequestHeader(value = "authorization") String token,
                                     @RequestBody World world){
        visualNutsService.authenticate(token);
        visualNutsService.exercise2(world);
        System.out.println(world.toString());
    return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
