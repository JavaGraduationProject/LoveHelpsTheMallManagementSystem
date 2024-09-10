package com.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Cart;
import com.service.CartService;
import com.entity.Users;
import com.entity.Goods;
import com.service.UsersService;
import com.service.GoodsService;
import com.util.PageHelper;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/cart" , produces = "text/plain;charset=utf-8")
public class CartController extends BaseController {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	private CartService cartService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private GoodsService goodsService;

	// 准备添加数据
	@RequestMapping("createCart.action")
	public String createCart() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Goods> goodsList = this.goodsService.getAllGoods();
		this.getRequest().setAttribute("goodsList", goodsList);
		return "admin/addcart";
	}
	// 添加数据
	@RequestMapping("addCart.action")
	public String addCart(Cart cart) {
		this.cartService.insertCart(cart);
		return "redirect:/cart/createCart.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteCart.action")
	public String deleteCart(String id) {
		this.cartService.deleteCart(id);
		return "redirect:/cart/getAllCart.action";
	}

	// 批量删除数据
	@RequestMapping("deleteCartByIds.action")
	public String deleteCartByIds() {
		String[] ids = this.getRequest().getParameterValues("cartid");
		for (String cartid : ids) {
			this.cartService.deleteCart(cartid);
		}
		return "redirect:/cart/getAllCart.action";
	}

	// 更新数据
	@RequestMapping("updateCart.action")
	public String updateCart(Cart cart) {
		this.cartService.updateCart(cart);
		return "redirect:/cart/getAllCart.action";
	}

	// 显示全部数据
	@RequestMapping("getAllCart.action")
	public String getAllCart(String number) {
		List<Cart> cartList = this.cartService.getAllCart();
		PageHelper.getPage(cartList, "cart", null, null, 10, number, this.getRequest(), null);
		return "admin/listcart";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryCartByCond.action")
	public String queryCartByCond(String cond, String name, String number) {
		Cart cart = new Cart();
		if(cond != null){
			if ("usersid".equals(cond)) {
				cart.setUsersid(name);
			}
			if ("goodsid".equals(cond)) {
				cart.setGoodsid(name);
			}
			if ("price".equals(cond)) {
				cart.setPrice(name);
			}
			if ("num".equals(cond)) {
				cart.setNum(name);
			}
			if ("addtime".equals(cond)) {
				cart.setAddtime(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.cartService.getCartByLike(cart), "cart", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/querycart";
	}

	// 按主键查询数据
	@RequestMapping("getCartById.action")
	public String getCartById(String id) {
		Cart cart = this.cartService.getCartById(id);
		this.getRequest().setAttribute("cart", cart);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Goods> goodsList = this.goodsService.getAllGoods();
		this.getRequest().setAttribute("goodsList", goodsList);
		return "admin/editcart";
	}


}
