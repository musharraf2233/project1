package com.comppack;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Accounts;
import com.servicepack.AccountService;

@Controller
@ComponentScan("com")
@RequestMapping("/")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
	public String homepage(Accounts account,ModelMap model) {
		
		List<Accounts> acc=service.findAll();
		model.addAttribute("account", account);
		return "home";
	}

	@RequestMapping(value = {"/"},method = RequestMethod.POST)
	public String login(@ModelAttribute("account") Accounts accounts,ModelMap model) {
		
		Accounts acc=service.findByusernameAndpassword(accounts.getUsername(), accounts.getPassword());
		
		System.out.println(accounts.getUsername());
		Accounts acc1=service.findAccountByName(accounts.getUsername());
		List<Accounts> list=service.findAccountByPass(accounts.getPassword());
		model.addAttribute("acc", acc);
			
		if(acc==null) {
			return "home";
		}else {
			return "login";
		}
	}
	@RequestMapping(value ={"/register"},method = RequestMethod.GET)
	public String addlogin1(ModelMap model) {
		Accounts account=new Accounts();
		model.addAttribute("account", account);
        model.addAttribute("edit", false);
		return "registration";
	}
	
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String addlogin(@Valid @ModelAttribute("account") Accounts account,BindingResult result,ModelMap model) {
//		model.addAttribute("account", account);
		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!service.isNameIsUnique(account.getId(), account.getUsername())) {
			FieldError ssnError =new FieldError("account","username",messageSource.getMessage("non.unique.username", new String[]{account.getUsername()}, Locale.getDefault()));
            result.addError(ssnError);
//         FieldError nameError=new FieldError("account", account.getusername(), null)
			return "registration";
		}
		System.out.println(account.getUsername());
		service.sendEmail(account.getEmail(), account.getFirstName()+" "+account.getLastName());
		service.createUser(account);
		return "welcome";
	}
}
