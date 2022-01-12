package com.application.skill.crud.controller;

import com.application.skill.i18.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * @author tangchao
 */
@Controller
public class UseI18Controller {

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("/getI18")
    public String getI18() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("success", null, locale);
        System.out.println(message);
        String error = MessageUtils.message("error");
        System.out.println("error:" + error);
        return "index";
    }
}
