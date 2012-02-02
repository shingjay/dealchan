package com.dealchan.backend.controller;

import com.dealchan.backend.domain.User;
import com.dealchan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private UserRepository userRepository;

    /*
     * Demonstrates a method accessed using GET at url /users
     * As you can see, you can request a model ( just a map ) to put in stuff
     * to be passed to the view
     * The return will be searching within WEB-INF/views/ for a matching JSP
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> userList = userRepository.findAll();
        
        Sort sort = new Sort( Sort.Direction.ASC, "username");
        
        List<User> userListSorted = userRepository.findAll( sort);
        model.addAttribute("userList", userList);
        model.addAttribute("sortedUserList", userListSorted);
        return "index";
    }


    /*
     * This method demonstrates the ability to get url parameter ( works for post too )
     * and using the value to store
     * and redirect to index when done
     */
    @RequestMapping(value = "seed", method = RequestMethod.GET)
    public String seed( @RequestParam String username) {
        if(username == null ) {

        } else {
            User user = new User();
            user.setUsername(username);
            userRepository.save(user);
        }
        return "redirect:/users";
    }
    
    

    /*
     * Shows how to retunr a json  - just return any java object
     * It is a good practice to create a DTO ( Data Transfer Object ) and
     * return only the necessary component
     */
    @RequestMapping(value = "json", method = RequestMethod.GET)
    public @ResponseBody List<User> json() {
        return userRepository.findAll();
    }

    /*
     * Example to show how to use restful url to return result
     * In this case, the get the user id from the url itself and display
     * The return type would be json --> since we will most likely be returning json
     */
    @RequestMapping(value = "{id}/json")
    @ResponseBody
    public User user(@PathVariable("id") Long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }


}
