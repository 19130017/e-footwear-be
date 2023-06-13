package vn.edu.hcmuaf.fit.efootwearspringboot.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;

import java.util.Collection;
import java.util.List;

public class AccountDomain implements UserDetails {
    private Account account;

    public AccountDomain(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(account.getRole().name()));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.getIsBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getIsVerified();
    }

    public Long getAccountId() {
        return this.account.getId();
    }

    public String getFullName() {
        return this.account.getCustomer().getLastName() + this.account.getCustomer().getFirstName();
    }

    public String getAvatar() {
        return this.account.getCustomer().getAvatar();
    }
}
