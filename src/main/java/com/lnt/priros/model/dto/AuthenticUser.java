package com.lnt.priros.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lnt.priros.model.entity.Firm;
import com.lnt.priros.model.entity.User;
import java.io.Serial;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Builder
@Getter
public class AuthenticUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username; // user의 아이디
    private String firmCode;
    private String part;
    private String fullName; // user의 이름

    @JsonIgnore
    private String password;
    private boolean enabled; // 계정 활성화여부
    private boolean accountNonExpired; // 계정 만료여부
    private boolean credentialsNonExpired; // 비밀번호 만료여부
    private boolean accountNonLocked; // 계정잠김여부
    private String role;
    private Collection<? extends GrantedAuthority> authorities;
    private Firm firm;

    public AuthenticUser(TokenClaims tokenClaims) {
        this.username = tokenClaims.getUserId();
        this.firmCode = tokenClaims.getFirmCode();
        this.part = tokenClaims.getPart();
        this.role = tokenClaims.getRole();
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.authorities = buildAuthorities(tokenClaims.getRole());
    }

    public static AuthenticUser build(User user) {
        Duration duration = Duration.between(user.getUpdated(), LocalDateTime.now());
        boolean accountNonExpired = !(duration.toDays() > 60);
        boolean credentialsNonExpired = false;

        if (user.getPasswordUpdate() != null) {
            duration = Duration.between(user.getPasswordUpdate(), LocalDateTime.now());
            credentialsNonExpired = !(duration.toDays() > 90);
        }
        return AuthenticUser.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .fullName(user.getUserName())
                .firmCode(user.getFirmCode())
                .part(user.getPosition())
                .role(user.getRoleCode())
                .accountNonExpired(accountNonExpired)
                .credentialsNonExpired(credentialsNonExpired)
                .enabled(!StringUtils.equals(user.getUseFlag(), "D"))
                .accountNonLocked(StringUtils.equals(user.getUseFlag(), "Y"))
                .authorities(AuthenticUser.buildAuthorities(user.getRoleCode())) // "ROLE_USER" "ROLE_OPERATOR"
                .build();
    }

    private static Collection<? extends GrantedAuthority> buildAuthorities(String role) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthenticUser user = (AuthenticUser) o;
        return Objects.equals(username, user.username);
    }
}
