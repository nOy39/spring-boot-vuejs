package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.Message;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.repository.MessageRepo;
import de.jonashackt.springbootvuejs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepo messageRepo;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @GetMapping(value = "/inbox")
    public @ResponseBody List<Message> getAllMessages() {
        LOG.info("Reading all message from database.");
        return messageRepo.findAll();
    }

    @PostMapping(value = "/inbox/{id}")
    public @ResponseBody Message setMessageStatus(@PathVariable("id") long id) {
        LOG.info("Reading message with id " + id + " from database.");
        Message message = messageRepo.findById(id).get();
        message.setRead(true);
        return messageRepo.save(message);
    }
}
