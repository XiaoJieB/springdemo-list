package com.luobo.controller;

import com.luobo.model.UserEntity;
import com.luobo.model.UserListForm;
import com.luobo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/")
    public String index(ModelMap model)
    {
        model.addAttribute("user_ct",userRepository.count());
        return "index";
    }
@RequestMapping("/users")
    public String users()
{
    return "users";
}
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(UserListForm userListForm, ModelMap model) {

        List<UserEntity> succeedList = new ArrayList<>();  // 添加成功
        List<UserEntity> failList = new ArrayList<>();     // 添加不成功
        for(UserEntity user : userListForm.getUsers()) {
            // 基本查重
            if(userRepository.countByFirstNameAndLastName(user.getFirstName(), user.getLastName()) == 0) {
                userRepository.saveAndFlush(user);
                succeedList.add(user);
            } else {
                failList.add(user);
            }
        }

        model.addAttribute("succeedList", succeedList);
        model.addAttribute("failList", failList);
        return "user_list";
    }
}
