package com.diego.exxhangesecurity.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.diego.exxhangesecurity.dto.UserPostRequest;
import com.diego.exxhangesecurity.entity.Authority;
import com.diego.exxhangesecurity.entity.User;
import com.diego.exxhangesecurity.repository.AuthorityRepository;
import com.diego.exxhangesecurity.repository.UserRepository;
import com.diego.exxhangesecurity.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/api/users")
@Validated
class UserController {

   private final UserRepository repository;

   private final PasswordEncoder passwordEncoder;

   private final AuthorityRepository authorityRepository;

   UserController(UserRepository repository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
       this.repository = repository;
       this.passwordEncoder = passwordEncoder;
       this.authorityRepository = authorityRepository;
   }

   @GetMapping
   Page<User> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, OAuth2Authentication authentication) {
       User principal = (User) authentication.getUserAuthentication().getPrincipal();
       
       if (Arrays.stream(principal.getAuthoritiesReadMode()).anyMatch(User.AUTHORITY_NAMES.ROLE_CLIENT.name()::equals)){
          return repository.findAllByUsername(principal.getUsername(), pageable);
       }
       return repository.findAll(pageable);
   }

   @GetMapping("/search")
   Page<User> search(@RequestParam String username, Pageable pageable, OAuth2Authentication authentication) {
    User principal = (User) authentication.getUserAuthentication().getPrincipal();

    if (Arrays.stream(principal.getAuthoritiesReadMode()).anyMatch(User.AUTHORITY_NAMES.ROLE_CLIENT.name()::equals)){
            return repository.findAllByUsernameContainsAndUsername(username, principal.getUsername(), pageable);
       }
       return repository.findByUsernameContains(username, pageable);
   }

   @GetMapping("/findByUsername")
   @PreAuthorize("hasAuthority('USER_READ') || (authentication.principal.username == #username)")
    User findByUsername(@RequestParam String username, OAuth2Authentication authentication) {
       return repository.findByUsername(username).orElseThrow(() -> new RuntimeException("username"));
   }

   @GetMapping("/{id}")
   @PostAuthorize("hasAuthority('USER_READ') || (returnObject != null && returnObject.username == authentication.principal.username)")
   User one(@PathVariable Long id) {
       return repository.findById(id).orElseThrow(() -> new RuntimeException("id"));
   }

   @PostMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   User create(@Valid @RequestBody UserPostRequest res) {
        User user = new User(res.getUsername(), passwordEncoder.encode(res.getPassword()));
        Authority client = authorityRepository.findByName(Constants.ROLE_ADMIN);
        Collection<Authority> authorities = new ArrayList<>();
        authorities.add(client);
        user.setAuthorities(authorities);        
        return repository.save(user);
   }

   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('USER_WRITE')")
   void delete(@PathVariable Long id) {
       if (repository.existsById(id)) {
        
           repository.deleteById(id);
       } else {
            throw new RuntimeException("user not found");   
        //throw new EntityNotFoundException(User.class, "id", id.toString());
       }
   }

   @PutMapping("/{id}")
   @PreAuthorize("hasAuthority('USER_WRITE') || (authentication.principal.username == @userRepository.findById(#id).orElse(new com.diego.exxhangesecurity.entity.User()).username)")
   void update(@PathVariable Long id, @Valid @RequestBody User res) {
       User u = repository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
       u.setUsername(res.getUsername());
       repository.save(u);
   }

   @PutMapping("/{id}/changePassword")
   @PreAuthorize("hasAuthority('USER_WRITE') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal.username == @userRepository.findById(#id).orElse(new com.diego.exxhangesecurity.entity.User()).username)")
   void changePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
       User user = repository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
       if (oldPassword == null || oldPassword.isEmpty() || passwordEncoder.matches(oldPassword, user.getPassword())) {
           user.setPassword(passwordEncoder.encode(newPassword));
           repository.save(user);
       } else {
           throw new ConstraintViolationException("old password doesn't match", new HashSet<>());
       }
   }
}