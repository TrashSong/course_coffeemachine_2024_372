package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Roles;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Users;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.RolesRepository;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.UserService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;
    private final RolesRepository rolesRepository;


    @GetMapping("/admin-users")
    public String getPageUsers(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "admin_users";
    }

    @GetMapping("/update-users")
    public String updateUsers(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "update-users";
    }

    @PostMapping("/updateUsers")
    public String updateUser(
                             @RequestParam(name = "id") Long id,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password
                             ){


        password = new BCryptPasswordEncoder().encode(password);
        userService.updateUser(password, username, id);

        return "redirect:/admin-users";
    }

    @GetMapping("/update-roles-users")
    public String updateRolesUser(Model model){

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", rolesRepository.findAll());

        return "update-roles-users";
    }

    @PostMapping("/updateRolesUsers")
    public String updateRolesUser(
            @RequestParam(name = "id") Users user,
            @RequestParam(name = "role") Roles role){

        Set<Roles> roles = user.getRolesset();
        boolean logic = false;

        for (Roles r : roles) {
            if(r.getId().equals(role.getId())) logic = true;
        }

        if(!logic) userService.addNewRoleToUser(user.getId(), role.getId());

        return "redirect:/update-roles-users";
    }

}
