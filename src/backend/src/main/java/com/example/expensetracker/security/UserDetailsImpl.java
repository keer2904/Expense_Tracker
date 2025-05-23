
package com.example.expensetracker.security;

import com.example.expensetracker.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    private final Long id;
    private final String name;
    private final String email;
    
    @JsonIgnore
    private final String password;
    
    private final Collection<? extends GrantedAuthority> authorities;
    
    public UserDetailsImpl(Long id, String name, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    public static UserDetailsImpl build(User user) {
        // For this simple app, all users have the same authority
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        
        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
