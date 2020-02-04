package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.service.AdvertService;

@RestController
@RequestMapping(value = "/adverts")
@RequiredArgsConstructor
@Slf4j
public class AdvertController {

    private final AdvertService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long create(@RequestBody Advert advert){
        return service.createAdvert(advert);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable("id") long id){
        return service.removeAdvert(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Advert update(@RequestBody Advert advert){
        return service.updateAdvert(advert);
    }

    @GetMapping("/{id}")
    public Advert get(@PathVariable("id") long id){
        return service.getAdvert(id);
    }
}
