package bg.softuni.Bakery.service;

import bg.softuni.Bakery.model.entities.UserRoleEntity;
import bg.softuni.Bakery.model.enums.UserRoleEnum;
import bg.softuni.Bakery.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initUserRole(){
        if (this.userRoleRepository.count()!=0){
           return;
        }
        Arrays.stream(UserRoleEnum.values()).forEach(roleEnum ->{
                    UserRoleEntity userRole =new UserRoleEntity();
                    userRole.setName(roleEnum);
                    this.userRoleRepository.save(userRole);
                }
        );
    }
}
