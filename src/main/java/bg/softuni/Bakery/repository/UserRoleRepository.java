package bg.softuni.Bakery.repository;

import bg.softuni.Bakery.model.entities.UserRoleEntity;
import bg.softuni.Bakery.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByName(UserRoleEnum name);
}
