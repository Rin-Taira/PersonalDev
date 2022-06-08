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
    	
    	String orderMsg = managerService.isCompleteTodayOrder();
    	
    	List<Order> myOrderList = orderService.findTodayOrderByUser(user.getId());
    	
    	session.setAttribute("user", user);
    	session.setAttribute("categoryList", categoryList);
    	session.setAttribute("menuListList", menuListList);
    	session.setAttribute("todayManager", todayManager);
    	session.setAttribute("myOrderList", myOrderList);
    	model.addAttribute("orderMsg", orderMsg);
    	
    	return "menu";
    }
    
//    @RequestMapping("/logout")
//    public String logout(@ModelAttribute("user") UserForm form, Model model) {
//    	session.invalidate();
//        return "logout";
//    }
//    
//    @RequestMapping(value="/sort", method=RequestMethod.GET)
//    public String sort(@RequestParam(name = "sort", defaultValue = "") String sortNum, @ModelAttribute("product") ProductForm form, Model model) {
//		
//		for (int i = 1; i < 7; i++) {
//			session.removeAttribute("s" + i);
//		}
//		
//		List<Product> productList = (List<Product>) session.getAttribute("productList");
//		
//
//		switch (sortNum) {
//		case "1":
//			Collections.sort(productList, new ProductCompareById());
//			break;
//
//		case "2":
//			Collections.sort(productList, new ProductCompareByCategory());
//			break;
//
//		case "3":
//			Collections.sort(productList, new ProductCompareByPrice1());
//			break;
//
//		case "4":
//			Collections.sort(productList, new ProductCompareByPrice2());
//			break;
//
//		case "5":
//			Collections.sort(productList, new ProductCompareByDate1());
//			break;
//
//		case "6":
//			Collections.sort(productList, new ProductCompareByDate2());
//			break;
//		}
//		
//		session.setAttribute("s" + sortNum, "selected");
//
//		session.setAttribute("productList", productList);
//    	
//    	return "menu";
//    }
//    
//    
//    @RequestMapping(value="/search", method=RequestMethod.GET)
//    public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, @ModelAttribute("product") ProductForm form, Model model) {
//    	
//    	for (int i = 1; i < 7; i++) {
//			session.removeAttribute("s" + i);
//		}
//    	
//    	List<Product> productList = Collections.emptyList();
//        
//        if (ParamUtil.isNullOrEmpty(keyword)) {
//        	productList = productService.find();
//        } else {
//        	productList = productService.findByKeyword(keyword);
//        	if (productList.size() == 0) {
//        		productList = productService.find();
//        		model.addAttribute("msg", "検索結果がありません");
//        	}
//        }
//        
//        session.setAttribute("productList", productList);
//    	
//    	return "menu";
//    }
//    
//    
    @RequestMapping(value="/orderCommit", method=RequestMethod.GET)
    public String orderCommit(@RequestParam(name = "id", defaultValue = "") String id, @RequestParam(name = "big", defaultValue = "") int bigFlag,
    		@RequestParam(name = "brown", defaultValue = "") int brownFlag, @RequestParam(name = "rice_big", defaultValue = "") int riceIncFlag, Model model) {
    	
    	Menu menu = menuService.findById(id);
    	
    	Order order = new Order(menu, (User) session.getAttribute("user"), brownFlag, bigFlag, riceIncFlag);
    	orderService.insertOrder(order);
    	
    	model.addAttribute("order", order);
    	
    	return "orderCommit";
    	
    }
    
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String review(@RequestParam(name = "id", defaultValue = "") String id, Model model) {
    	
    	
    	Menu menu = menuService.findById(id);
    	
    	List<Review> reviewList = reviewService.findById(id);
    	
    	model.addAttribute("menu", menu);
    	model.addAttribute("reviewList", reviewList);
    	
    	return "review";
    	
    }
    
    @RequestMapping("/updateManager")
    public String updateManager(@ModelAttribute("user") UserForm form, Model model) {

    	managerService.updateManager();
    	User todayManager = userService.findById(managerService.getTodayManager());
    	session.setAttribute("todayManager", todayManager);
        return "menu";
    }
    
    @RequestMapping("/orderDetail")
    public String orderDetail(@ModelAttribute("user") UserForm form, Model model) {

    	List<Order> orderList = orderService.findTodayOrder();
    	
    	model.addAttribute("orderList", orderList);
    	
        return "orderDetail";
    }
    
    @RequestMapping("/deleteTodayOrder")
    public String deleteTodayOrder(@ModelAttribute("user") UserForm form, Model model) {

    	User user = (User) session.getAttribute("user");
    	orderService.deleteTodayOrder(user.getId());
    	
    	List<Order> myOrderList = orderService.findTodayOrderByUser(user.getId());
    	
    	session.setAttribute("myOrderList", myOrderList);
    	model.addAttribute("msg", "削除が完了しました");
    	
        return "menu";
    }
    
    @RequestMapping(value="/reviewPost", method=RequestMethod.GET)
    public String reviewPost(@RequestParam(name = "menuId", defaultValue = "") String menuId, @Validated @ModelAttribute("review") ReviewForm form, BindingResult bindingResult, Model model) {
    	
    	Menu menu = menuService.findById(menuId);
    	
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
    	
    	return "menu";
    	
    }
//    
//    @RequestMapping("/return")
//    public String top(@ModelAttribute("product") ProductForm form,  Model model) {
//    	return "menu";
//    }
//    
//    @RequestMapping("/updateInput")
//    public String updateInput(@ModelAttribute("product") ProductForm form, Model model) {
//    	return "updateInput";
//    }
//    
//    @RequestMapping(value="/delete", method=RequestMethod.GET)
//    public String delete(@RequestParam(name = "id", defaultValue = "") String id, @ModelAttribute("product") ProductForm form, Model model) {
//    	
//    	int result = productService.deleteById(id);
//    	
//    	if (result == -1) {
//			model.addAttribute("msg", "削除に失敗しました。");
//			return "detail";
//		}
//
//		model.addAttribute("msg", "削除に成功しました。");
//		
//		List<Product> productList = productService.find();
//
//		session.setAttribute("productList", productList);
//		
//		return "menu";
//	
//    }
//    
//    @RequestMapping(value="/update", method=RequestMethod.GET)
//    public String update(@RequestParam(name = "category", defaultValue = "") Integer id, @Validated @ModelAttribute("product") ProductForm productForm, BindingResult bindingResult, Model model) {
//    	
//    	if (bindingResult.hasErrors()) {
//    		return "updateInput";
//    	}
//    	
//    	if (productForm.getProductId() != (Integer) session.getAttribute("currentId") && productService.findById(String.valueOf(productForm.getProductId())) != null) {
//    		model.addAttribute("msg", "商品IDが重複しています");
//    		List<Category> categoryList = categoryService.find();
//        	model.addAttribute("categoryList", categoryList);
//    		return "updateInput";
//    	}
//    	
//    	int result = productService.updateById(productForm.getProductId(), productForm.getName(), productForm.getPrice(), id, productForm.getDescription(), (Integer) session.getAttribute("currentId"));
//    	
//    	if (result == -1) {
//    		model.addAttribute("msg", "更新時にエラーが発生しました");
//    		return "updateInput";
//    	}
//    	
//    	model.addAttribute("msg", "更新処理が完了しました。");
//    	
//    	List<Product> productList = productService.find();
//
//    	session.setAttribute("productList", productList);
//    	
//    	return "menu";
//	
//    }
//    
//    @RequestMapping("/insert")
//    public String insert(@ModelAttribute("product") ProductForm form,  Model model) {
//    	return "insert";
//    }
//    
//    @RequestMapping(value="/insertCommit", method=RequestMethod.GET)
//    public String insertCommit(@RequestParam(name = "category", defaultValue = "") Integer id, @Validated @ModelAttribute("product") ProductForm form, BindingResult bindingResult, Model model) {
//    	
//    	if (bindingResult.hasErrors()) {
//    		return "insert";
//    	}
// 
//    	
//    	if (productService.findById(String.valueOf(form.getProductId())) != null) {
//    		model.addAttribute("msg1", "商品IDが重複しています");
//    		return "insert";
//    	}
//    	
//    	Product product = new Product(form.getProductId(), id, form.getCategoryName(), form.getName(), form.getPrice(), form.getDescription(), new Timestamp(System.currentTimeMillis()), null);
//    	int result = productService.insert(product);
//    	
//    	if (result == -1) {
//    		model.addAttribute("msg1", "更新時にエラーが発生しました");
//    		return "insert";
//    	}
//    	
//    	model.addAttribute("msg", "更新処理が完了しました。");
//    	
//    	List<Product> productList = productService.find();
//
//    	session.setAttribute("productList", productList);
//    	
//    	return "menu";
//	
//    }
//    
}

