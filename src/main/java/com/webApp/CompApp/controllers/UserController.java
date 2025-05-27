package com.webApp.CompApp.controllers;

import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.security.SecurityConfig;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.StationService;
import com.webApp.CompApp.services.UserService;
import com.webApp.CompApp.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Controller
public class UserController {
    
	private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

	@GetMapping("/profile")
	public String Profile(Model model) {

		User user = userService.GetCurrentUser();
        if (user == null) return "authorization"; 

		Station station = user.getStation();
        if (station != null)  model.addAttribute("station", station);

		model.addAttribute("user", user);
		
		model.addAttribute("title", "Профиль");
		return "profile";
	}

	@GetMapping("/changeData")
	public String changeData(Model model) {

		User user = userService.GetCurrentUser();
		model.addAttribute("user", user);
		model.addAttribute("title", "Изменение данных");
		return "changeData";
	}

	@PostMapping("/changeData")
	public String PostChangeData(@RequestParam String name, @RequestParam String surname, @RequestParam String login,
	 							  @RequestParam String password, @RequestParam String checkPassword, @RequestParam String role, Model model) {
		
		User user = userService.GetCurrentUser();
		if ((userService.findByLogin(login) != null) && (!user.getLogin().equals(login))) {
			model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
			return "changeData"; 
		}

		// if(!password.equals(checkPassword)){
		// 	model.addAttribute("errorMessage", "Пароли не совпадают");
		// 	return "changeData"; 
		// }
		
		userService.changeData(user, name, surname, login, password, role );
		return "redirect:/profile"; 
								

	}


    @GetMapping("/registration")
	public String Registration(Model model) {
		model.addAttribute("title", "Регистрация");
		return "registration";
	}

	@PostMapping("/registration")
	public String RegistrationAct(@RequestParam String name, @RequestParam String surname, @RequestParam String login,
	 							  @RequestParam String password, @RequestParam String role, Model model) {
		
		if (userService.findByLogin(login) != null) {
			model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
			return "registration"; 
		}
		
		User user = new User(name, surname, login, password, role);
		userService.registerUser(user);
		return "redirect:/authorization"; 
								

	}



	@GetMapping("/authorization")
	public String Authorization(Model model) {
		model.addAttribute("title", "Авторизация");
		return "authorization";
	}

	
	
}

