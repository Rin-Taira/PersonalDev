package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.form.MenuForm;
import com.example.demo.controller.form.ReviewForm;
import com.example.demo.controller.form.UserForm;
import com.example.demo.entity.Category;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ManagerService;
import com.example.demo.service.MenuService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@Controller
public class SystemController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    HttpSession session;
    
    @RequestMapping("/index")
    public String index(@ModelAttribute("user") UserForm form, Model model) {

        return "index";
    }
    
    @RequestMapping("/returnMenu")
    public String returnMenu(@ModelAttribute("user") UserForm form, Model model) {

    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
        return "menu";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Validated @ModelAttribute("user") UserForm form, BindingResult bindingResult, @ModelAttribute("product") MenuForm menuForm, Model model) {
    	
    	if (bindingResult.hasErrors()) {
    		return "index";
    	}
    	User user = userService.authentication(form.getLoginId(), form.getPassword());
    	if (user == null) {
    		model.addAttribute("msg1", "IDまたはパスワードが不正です");
    		return "index";
    	}
    	
    	List<Category> categoryList = categoryService.find();
    	
    	List<List<Menu>> menuListList = new ArrayList<List<Menu>>();
    	
    	for (int i = 1; i <= categoryList.size(); i++) {
    		menuListList.add(menuService.findByCategory(i));
    	}

    	User todayManager = userService.findById(managerService.getTodayManager());
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	List<Order> myOrderList = orderService.findTodayOrderByUser(user.getId());
    	
    	
    	session.setAttribute("user", user);
    	session.setAttribute("userName", user.getName());
    	session.setAttribute("categoryList", categoryList);
    	session.setAttribute("menuListList", menuListList);
    	session.setAttribute("todayManager", todayManager);
    	session.setAttribute("myOrderList", myOrderList);
    	model.addAttribute("orderFlag", orderFlag);
    	
    	return "menu";
    }
    
    @RequestMapping(value="/logout")
	public String logout(@ModelAttribute("user") UserForm form) {
		session.invalidate();
		return "index";
	}

    @RequestMapping(value="/orderCommit", method=RequestMethod.GET)
    public String orderCommit(@RequestParam(name = "id", defaultValue = "") String id, @RequestParam(name = "big", defaultValue = "") int bigFlag,
    		@RequestParam(name = "brown", defaultValue = "") int brownFlag, @RequestParam(name = "rice_big", defaultValue = "") int riceIncFlag, Model model) {
    	
    	User user = (User) session.getAttribute("user");
    	
    	Menu menu = menuService.findById(id);
    	
    	Order order = new Order(menu, (User) session.getAttribute("user"), brownFlag, bigFlag, riceIncFlag);
    	orderService.insertOrder(order);
    	
    	List<Order> myOrderList = orderService.findTodayOrderByUser(user.getId());
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
    	session.setAttribute("myOrderList", myOrderList);
    	
    	return "menu";
    	
    }
    
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String review(@RequestParam(name = "id", defaultValue = "") String id, Model model) {
    	
    	
    	Menu menu = menuService.findById(id);
    	
    	List<Review> reviewList = reviewService.findById(id);
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
    	model.addAttribute("menu", menu);
    	model.addAttribute("reviewList", reviewList);
    	
    	return "review";
    	
    }
    
    @RequestMapping("/updateManager")
    public String updateManager(@ModelAttribute("user") UserForm form, Model model) {

    	managerService.updateManager();
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
        return "menu";
    }
    
    @RequestMapping("/orderDetail")
    public String orderDetail(@ModelAttribute("user") UserForm form, Model model) {

    	List<Order> orderList = orderService.findTodayOrder();
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
    	model.addAttribute("orderList", orderList);
    	
        return "orderDetail";
    }
    
    @RequestMapping("/deleteTodayOrder")
    public String deleteTodayOrder(@ModelAttribute("user") UserForm form, Model model) {

    	User user = (User) session.getAttribute("user");
    	orderService.deleteTodayOrder(user.getId());
    	
    	List<Order> myOrderList = orderService.findTodayOrderByUser(user.getId());
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	session.setAttribute("myOrderList", myOrderList);
    	model.addAttribute("orderFlag", orderFlag);
    	
        return "menu";
    }
    
    @RequestMapping(value="/reviewPost", method=RequestMethod.GET)
    public String reviewPost(@RequestParam(name = "menuId", defaultValue = "") String menuId, @Validated @ModelAttribute("review") ReviewForm form, BindingResult bindingResult, Model model) {
    	
    	Menu menu = menuService.findById(menuId);
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
    	model.addAttribute("menu", menu);
    	
    	return "reviewPost";
    	
    }
    
    @RequestMapping(value="/reviewCommit", method=RequestMethod.GET)
    public String reviewCommit(@RequestParam(name = "menuName", defaultValue = "") String menuName, @Validated @ModelAttribute("review") ReviewForm form, BindingResult bindingResult, Model model) {
    	
    	if (bindingResult.hasErrors()) {
    		return "reviewPost";
    	}
    	
    	Review review = new Review(form.getMenuId(), null, form.getUserId(), null, form.getStar(), form.getReview());
    	
    	reviewService.insertReview(review);
    	
    	model.addAttribute("msg", "レビューの投稿が完了しました");
    	
    	List<List<Menu>> menuListList = new ArrayList<List<Menu>>();
    	
    	List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
    	
    	for (int i = 1; i <= categoryList.size(); i++) {
    		menuListList.add(menuService.findByCategory(i));
    	}
    	
    	int orderFlag = managerService.isCompleteTodayOrder();
    	
    	model.addAttribute("orderFlag", orderFlag);
    	session.setAttribute("menuListList", menuListList);

    	
    	return "menu";
    	
    }

}

