package com.diego.exxhangesecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User implements UserDetails{

    public enum AUTHORITY_NAMES {
        ROLE_CLIENT, ROLE_ADMIN;
    } 


    public User(){
    }

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), 
        inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Authority> authorities;

    public void setId(Long id){
        this.id = id;
    }

    public void setUsername(String email){
        this.username = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public Collection<Authority> getAuthorities(){
        return this.authorities;
    }
    
    public String[] getAuthoritiesReadMode(){

        String[] result = this.authorities.stream()
			.map(x -> x.getName())
            .toArray(String[]::new);
        return result;
    }

    public void setAuthorities(Collection<Authority> authorities){
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}