package bg.softuni.Bakery.service;

import bg.softuni.Bakery.model.dto.UserRegisterDTO;
import bg.softuni.Bakery.model.entities.AddressEntity;
import bg.softuni.Bakery.model.entities.UserEntity;
import bg.softuni.Bakery.model.entities.UserRoleEntity;
import bg.softuni.Bakery.model.enums.UserRoleEnum;
import bg.softuni.Bakery.repository.AddressRepository;
import bg.softuni.Bakery.repository.UserRepository;
import bg.softuni.Bakery.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, AddressRepository addressRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.userDetailsService = userDetailsService;
    }

    public void initAdmin() {
        if (this.userRepository.count() == 0) {
            UserRoleEntity admin = this.userRoleRepository.findByName(UserRoleEnum.ADMIN);
            //  List<UserRoleEntity> userRoleEntities = new ArrayList<>();
            //List<AddressEntity> addressEntities = new ArrayList<>();
            //userRoleEntities.add(admin);
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setDetails(null);
            addressEntity.setStreet("Street");
            addressEntity.setPostalCode("1000");
            addressEntity.setCity("Sofia");

            UserEntity userEntity = new UserEntity();

            userEntity.setFirstName("Admin");
            userEntity.setLastName("Adminov");
            userEntity.setEmail("admin@admin.com");
            userEntity.setUserRoles(Set.of(admin));
            userEntity.setPhone("088888888");
            userEntity.setAddresses(List.of(addressEntity));
            userEntity.setPassword(passwordEncoder.encode("1111"));
            this.addressRepository.save(addressEntity);

            userEntity = this.userRepository.save(userEntity);
            addressEntity.setEmployee(userEntity);

            this.addressRepository.save(addressEntity);

        }
    }


    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = new UserEntity();
        AddressEntity userAddress = new AddressEntity();

        userAddress.setCity(userRegisterDTO.getCity());
        userAddress.setPostalCode(userRegisterDTO.getPostalCode());
        userAddress.setStreet(userRegisterDTO.getStreet());
        userAddress.setDetails(userRegisterDTO.getDetails());

        user.setEmail(userRegisterDTO.getEmail());
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setPhone(userRegisterDTO.getPhone());


        UserRoleEntity userRole = this.userRoleRepository.findByName(UserRoleEnum.USER);
        user.setUserRoles(Set.of(userRole));

        this.addressRepository.save(userAddress);

        user.setAddresses(List.of(userAddress));
        userAddress.setEmployee(user);
        this.userRepository.save(user);
        this.addressRepository.save(userAddress);
    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    public boolean isCurrentUserAdmin(Principal principal) {
        UserEntity user = userRepository.findByEmail(principal.getName()).orElse(null);
        assert user != null;
        return user.getUserRoles().stream().anyMatch(r-> r.getName().equals(UserRoleEnum.ADMIN));
    }
}
