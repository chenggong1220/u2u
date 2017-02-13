package com.u2u.framework.sys.authorize.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.u2u.framework.base.BaseController;

/**
 * @ClassName: BaseDirectoryController <br>
 * @Description: TODO <br>
 * @date 2015-4-2 下午4:27:32 <br>
 * 
 * @author Freud
 */
@EnableWebMvc
@RequestMapping
@Controller("baseDirectionController")
public class BaseDirectionController extends BaseController
{
    @RequestMapping({"", "/", "/index"})
    public String baseMain()
    {
        return "redirect:auth/main";
    }
}
