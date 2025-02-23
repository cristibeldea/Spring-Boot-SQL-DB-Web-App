package com.example.loginAndWelcome.controllers;
import com.example.loginAndWelcome.entities.Users;
import com.example.loginAndWelcome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class loginController {

    @Autowired
    private UserRepository UserRepo;
    List<Users> listaUseri;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        listaUseri = UserRepo.getAllUsers();
        for(Users userIter: listaUseri){
            if(userIter.getUsername().equals(username)){
                if(userIter.getPassword().equals(password)){
                    if(userIter.getIsAdmin()) {
                        redirectAttributes.addFlashAttribute("mesaj_eroare",
                                "Autentificare cu succes!");
                        return "redirect:/welcome";
                    }
                    else{
                        redirectAttributes.addFlashAttribute("mesaj_eroare",
                                "Contul nu are atribut de administrator!");
                        return "redirect:/login";
                    }
                }
                else{
                    redirectAttributes.addFlashAttribute("mesaj_eroare",
                            "Parola incorecta!");
                    return "redirect:/login";
                }
            }
        }
        redirectAttributes.addFlashAttribute("mesaj_eroare",
                "Nu exista un astfel de user!");
        return "redirect:/login";
    }
}