package guru.qa.rococo.controller;

import guru.qa.rococo.model.UserJson;
import guru.qa.rococo.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserDataService userService;

    @Autowired
    public UserController(UserDataService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UserJson currentUser(@RequestParam String username) {
        return userService.getCurrentUser(username);
    }

    @PatchMapping()
    public UserJson updateUserInfo(@RequestBody UserJson user) {
        return userService.update(user);
    }
}
