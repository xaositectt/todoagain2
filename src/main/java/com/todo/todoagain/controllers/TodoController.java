package com.todo.todoagain.controllers;


import com.todo.todoagain.repository.TodoRepo;
import com.todo.todoagain.todo.Todo;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

  @Autowired
  TodoRepo todoRepo;

  @GetMapping(value = "/todo")
  public String showdata(Model model,
      @RequestParam(value = "isit", required = false) Boolean isit) {
    List<Todo> todos;

    if (isit == null) {
      todos = (List<Todo>) todoRepo.findAll();

    } else if (isit) {
      todos = StreamSupport.stream(todoRepo.findAll().spliterator(), false)
          .filter(p -> p.getIsdone())
          .collect(Collectors.toList());

    } else {
      todos = StreamSupport.stream(todoRepo.findAll().spliterator(), false)
          .filter(p -> !p.getIsdone())
          .collect(Collectors.toList());
    }
    //this is needed if the button on at the same page. We create an empty object with this,
    //create the attributes in the form, link the form to an endpoint where the method
    //adds it to the repository, and redirect back to the main view.
    // model.addAttribute("newTodo", new Todo());
    model.addAttribute("todos", todos);
    return "main";
  }

  @GetMapping(value = "/addd")
  public String addNew(Model model) {
    model.addAttribute("newTodo", new Todo());
    return "add_data";
  }

  //I pass the object from the input fields to this @ModelAttribute bullshit. Apparently
  //it needs to be stored in a method like this, object type has to be correct, otherwise
  //the form with the object variables doesn't work.

//  @ModelAttribute(value = "newTodo")
//  public Todo myTodo() {
//    return new Todo();
//  }

  //apparently the object gets passed to this @Modellatribute where the form takes us.
  //Here I can save it to the repo.

  @PostMapping(value = "/todo/addd/add")
  public ModelAndView add(@ModelAttribute Todo todo) {
    todo.setDate(new Date());
    todoRepo.save(todo);
    return new ModelAndView("redirect:/todo");
  }

  @GetMapping("/delete/{todoid}")
  public ModelAndView delete(@PathVariable Integer todoid) {
    todoRepo.delete(todoid);
    return new ModelAndView("redirect:/todo");
  }

  @GetMapping("/edit/{todoid}")
  public String editForm(@PathVariable int todoid, Model model) {
    Todo todo = todoRepo.findOne(todoid);
    model.addAttribute("newTodo", todo);
    return "edit";
  }

  @PostMapping("/edit/{todoid}")
  public ModelAndView edit(@PathVariable int todoid, @ModelAttribute Todo todo) {
    todo.setId(todoid);
    todoRepo.save(todo);
    return new ModelAndView("redirect:/todo");
  }

//  @PostMapping("/edit/{todoid}/")
//  public ModelAndView edit(@PathVariable int todoid,  @ModelAttribute Todo todo){
//    todoRepo.save(todo);
//    return new ModelAndView("redirect:/todo");
//  }
}

