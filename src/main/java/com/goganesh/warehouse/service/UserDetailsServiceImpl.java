package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.MyUserPrincipal;
import com.goganesh.warehouse.domain.Authority;
import com.goganesh.warehouse.domain.User;
import com.goganesh.warehouse.repository.AuthorityRepository;
import com.goganesh.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public MyUserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<Authority> authorities = authorityRepository.findByUser(user);

        return new MyUserPrincipal(user, authorities);
    }
}
