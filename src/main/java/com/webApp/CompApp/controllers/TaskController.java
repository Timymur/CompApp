package com.webApp.CompApp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.Task;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.TaskService;
import com.webApp.CompApp.services.UserService;
import com.webApp.CompApp.services.WorkShiftService;

@Controller
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;


    
    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/addTask")
	public String addTask(Model model) {
        
		model.addAttribute("title", "Добавление задания");
		return "addTask";

	}

    @PostMapping("/addTask")
	public String postAddTask(@RequestParam String theme, @RequestParam String text, Model model) {
        
        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

        Station station = user.getStation();
        if (station == null)   return "redirect:/";

        Task task = new Task(station, user, theme, text);
        taskService.save(task);
		
		return "redirect:/";

	}

    @GetMapping("/task/{id}")
    public String taskData(@PathVariable(value="id") Long taskId, Model model) {
        
        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/";

        Task task = taskService.findById(taskId).orElse(null);
        model.addAttribute("task", task);
        model.addAttribute("title", "Задание");
        model.addAttribute("user", user);
        return "task";
    }

    @PostMapping("/completeTask")
    public String completeTask(@RequestParam Long taskId, Model model) {
        
        Task task = taskService.findById(taskId).orElse(null);
        task.setStatus(true);
        taskService.save(task);

        return "redirect:/";
    }

    @GetMapping("/archiveTask")
    public String archiveTask(Model model) {
        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/";

        Station station = user.getStation();
        if (station == null)   return "redirect:/";

        List<Task> tasks = taskService.findByStationIdAndStatusTrue(station.getId());
        if(tasks.size() == 0)  return "redirect:/";

        model.addAttribute("title", "Архив заданий");
        model.addAttribute("tasks", tasks);

        return "archiveTask";

    }
    
    

}
