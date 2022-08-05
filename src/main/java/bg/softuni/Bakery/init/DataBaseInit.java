package bg.softuni.Bakery.init;

import bg.softuni.Bakery.service.CategoryService;
import bg.softuni.Bakery.service.UserRoleService;
import bg.softuni.Bakery.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {
    private final UserRoleService userRoleService;
    private final CategoryService categoryService;
    private final UserService userService;

    public DataBaseInit(UserRoleService userRoleService, CategoryService categoryService, UserService userService) {
        this.userRoleService = userRoleService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRoleService.initUserRole();
        this.categoryService.initCategory();
        this.userService.initAdmin();
    }
}
