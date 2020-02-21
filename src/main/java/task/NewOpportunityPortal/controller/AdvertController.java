package task.NewOpportunityPortal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import task.NewOpportunityPortal.entity.Advert;
import task.NewOpportunityPortal.service.AdvertService;

import java.util.List;

@RestController
@RequestMapping(value = "/adverts")
@RequiredArgsConstructor
@Slf4j
public class AdvertController {

    private final AdvertService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public long create(@RequestBody Advert advert) {
        log.info("Create advert {}", advert.toString());
        return service.createAdvert(advert);
    }

    @DeleteMapping("/{advertId}")
    public void remove(@PathVariable("advertId") long advertId) {
        log.info("Remove advert {}", advertId);
        service.removeAdvert(advertId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Advert update(@RequestBody Advert advert) {
        log.info("Update advert {}", advert.toString());
        return service.updateAdvert(advert);
    }

    @GetMapping("/{advertId}")
    public Advert get(@PathVariable("advertId") long advertId) {
        log.info("Get advert {}", advertId);
        return service.getAdvert(advertId);
    }

    @GetMapping
    public List<Long> getAllIdAdverts() {
        log.info("Get all adverts");
        return service.getAllIdAdverts();
    }
}
