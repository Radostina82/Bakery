package bg.softuni.Bakery.service;

import bg.softuni.Bakery.model.entities.UserEntity;
import bg.softuni.Bakery.model.entities.UserRoleEntity;
import bg.softuni.Bakery.model.user.BakeryUserDetails;
import bg.softuni.Bakery.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.stream.Collectors;


public class BakeryUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public BakeryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity user) {
        /*return
                User.builder().
                        username(user.getEmail()).
                        password(user.getPassword()).
                        authorities(user.getUserRoles()
                                .stream()
                                .map(this::map)
                                .collect(Collectors.toList()))
                        .build();*/
return new BakeryUserDetails(user.getPassword(),
        user.getEmail(),
        user.getFirstName(),
        user.getLastName(),
        user.getUserRoles().
                stream().
                map(this::map).
                collect(Collectors.toList()));
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getName().name());
    }
}
