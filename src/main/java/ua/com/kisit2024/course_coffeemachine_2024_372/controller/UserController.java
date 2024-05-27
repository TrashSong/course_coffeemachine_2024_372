package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Roles;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Users;
import ua.com.kisit2024.course_coffeemachine_2024_372.service.UserService;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String getPageRegistration(Model model){

        model.addAttribute("users", new Users());

        return "registration";
    }

    @PostMapping("/registration")
    public String saveNewUserToDb(@Valid Users user, BindingResult bindingResult1) {

        // Val User
        if(bindingResult1.hasErrors()){
            return "registration";
        }

        // Username in DB
        if (userService.getLogicByUsername(user.getUsername())){
            return "redirect:/registration";
        }

        user.setRolesset(Collections.singleton(new Roles(1L, "ROLE_User")));

        // User (+id) = save User to DB
        Users user1 = userService.saveNewUserToDB(user);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String getAuth(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password,
                          HttpServletRequest request) {

        if (!userService.getLogicByUsernameAndPassword(username, password)){
            return "redirect:/registration";
        } else {

            HttpSession session = request.getSession();

            Users user = userService.getUserByUsername(username);
            session.setAttribute("user", user.getId());

            return "redirect:/order";
        }
    }

}
