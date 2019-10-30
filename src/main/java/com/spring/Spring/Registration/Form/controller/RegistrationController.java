package com.spring.Spring.Registration.Form.controller;

import com.spring.Spring.Registration.Form.entity.User;
import com.spring.Spring.Registration.Form.service.UserService;
import com.spring.Spring.Registration.Form.validation.UserRegistratrionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public UserRegistratrionDto userRegistratrionDto() {
        return new UserRegistratrionDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistratrionDto userRegistratrionDto,
                                      BindingResult result) {
        User existing = userService.findByEmail(userRegistratrionDto().getEmail());

        if (existing !=null ) {
            result.rejectValue("email",null,"There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userRegistratrionDto);

        return "redirect:/registration?success";
    }

}
