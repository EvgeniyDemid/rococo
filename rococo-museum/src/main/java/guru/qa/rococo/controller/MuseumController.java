package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/museum")
public class MuseumController {

  private final MuseumService museumService;

  @Autowired
  public MuseumController(MuseumService museumService) {
    this.museumService = museumService;
  }

  @GetMapping()
  public Page<MuseumJson> getAll(@RequestParam(required = false) String title,
                                 @PageableDefault Pageable pageable) {
    return museumService.getAll(title, pageable);
  }

  @GetMapping("/{id}")
  public MuseumJson findMuseumById(@PathVariable("id") String id) {
    return museumService.findMuseumById(id);
  }

  @PatchMapping()
  public MuseumJson updateMuseum(@RequestBody MuseumJson museum) {
    return museumService.update(museum);
  }

  @PostMapping()
  public MuseumJson addMuseum(@RequestBody MuseumJson museum) {
    return museumService.add(museum);
  }
}
