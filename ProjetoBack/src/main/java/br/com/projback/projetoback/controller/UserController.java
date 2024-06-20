package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.request.UserRequest;
import br.com.projback.projetoback.response.TokenResponse;
import br.com.projback.projetoback.security.authentication.JwtTokenService;
import br.com.projback.projetoback.security.userdetails.UserDetailsImpl;
import br.com.projback.projetoback.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid UserRequest request) throws LojistaException {

        User newUser = this.userService.create(request.getUsername(), request.getPassword(), request.getProfile_id());

        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest request) throws LojistaException{
        User user = this.userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        String jwtToken = jwtTokenService.generateToken(new UserDetailsImpl(user));

        TokenResponse response = new TokenResponse();
        response.setToken(jwtToken);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get/byId/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User response = this.userService.getById(id);

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
