package com.example.demo.web;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MemberEntity;
import com.example.demo.form.AutentificaForm;
import com.example.demo.service.impl.MemberServiceImpl;

@Controller
public class AutentificaController {


	MemberServiceImpl memberService;
	
    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/mypage")
    public String mypage(Principal principal, Model model) {
		
    	Authentication authentication = (Authentication) principal;
		MemberEntity user = (MemberEntity) authentication.getPrincipal();
				
		model.addAttribute("user", user);
      return "myPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("AutentificaForm", new AutentificaForm());
        return "login";
    }



}