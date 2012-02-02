package com.dealchan.backend.controller;

import com.dealchan.backend.domain.User;
import com.dealchan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/1/12
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/users")
public class HomeController {

    @Autowired
    private UserRepository userService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> userList = userService.findAll();
        
        Sort sort = new Sort( Sort.Direction.ASC, "username");
        
        List<User> userListSorted = userService.findAll( sort);
        model.addAttribute("userList", userList);
        model.addAttribute("sortedUserList", userListSorted);
        return "index";
    }
    
    @RequestMapping(value = "seed", method = RequestMethod.GET)
    public String seed( @RequestParam String username) {
        if(username == null ) {

        } else {
            User user = new User();
            user.setUsername(username);
            userService.save(user);
        }
        return "redirect:/users";
    }


}
