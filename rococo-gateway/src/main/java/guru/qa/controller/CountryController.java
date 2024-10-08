package guru.qa.controller;

import guru.qa.model.CountryJson;
import guru.qa.service.api.RestCountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final RestCountryClient geoClient;

    @Autowired
    public CountryController(RestCountryClient geoClient) {
        this.geoClient = geoClient;
    }

    @GetMapping()
    public Page<CountryJson> getAll(@RequestParam(required = false) String name,
                                    @PageableDefault Pageable pageable) {
        return geoClient.getAll(name, pageable);
    }
}
