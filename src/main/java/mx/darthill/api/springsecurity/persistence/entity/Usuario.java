package mx.darthill.api.springsecurity.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import mx.darthill.api.springsecurity.persistence.util.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table (name = "Usuario")
public class Usuario implements UserDetails {

    private String username;
    private String usuario;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(role == null) return null;

        if(role.getPermissions() == null) return null;

        return role.getPermissions().stream()
                .map(each -> {
                    String permission = each.name();
                    return new SimpleGrantedAuthority(permission);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
