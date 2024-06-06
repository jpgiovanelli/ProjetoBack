package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.request.UserRequest;
import br.com.projback.projetoback.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequest request) throws LojistaException {

        User newUser = this.userService.create(request.getUsername(), request.getPassword(), request.getProfile_id());

        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User response = this.userService.getById(id);

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
