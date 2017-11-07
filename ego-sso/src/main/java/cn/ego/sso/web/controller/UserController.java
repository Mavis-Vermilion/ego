package cn.ego.sso.web.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.sso.service.UserService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Object userCheck(@PathVariable String param,@PathVariable Integer type,String callback) {
		EgoResult egoResult = userService.userCheck(param, type);
		if(StringUtils.isNoneBlank(callback)) {
			MappingJacksonValue value = new MappingJacksonValue(egoResult);
			value.setJsonpFunction(callback);
			return value;
		}
		String json = JsonUtils.objectToJson(egoResult);
		return json;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult userLogin(UserVo userVo,HttpServletRequest request,HttpServletResponse response) {
		return userService.userLogin(userVo, request, response);
	}
	
	@RequestMapping(value="/token/{token}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Object userToken(@PathVariable String token,String callback) {
		EgoResult egoResult = userService.userToken(token);
		if(StringUtils.isNoneBlank(callback)) {
			MappingJacksonValue value = new MappingJacksonValue(egoResult);
			value.setJsonpFunction(callback);
			return value;
		}
		String json = JsonUtils.objectToJson(egoResult);
		return json;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult userRegister(UserVo userVo) {
		if(Objects.isNull(userVo)) 
			return EgoResult.build(400, "注册失败. 请校验数据后请再提交数据.");
		return userService.userRegister(userVo);
	}
	
	@RequestMapping(value="/logout/{token}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Object userLogout(@PathVariable String token,String callback) {
		if(StringUtils.isBlank(token))
			return EgoResult.build(400, "token参数异常");
		EgoResult egoResult = userService.userLogout(token);
		if(StringUtils.isNoneBlank(callback)) {
			MappingJacksonValue value = new MappingJacksonValue(egoResult);
			value.setJsonpFunction(callback);
			return value;
		}
		String json = JsonUtils.objectToJson(egoResult);
		return json;
	}
	
	@RequestMapping("/showLogin")
	public String showLoginUI(String redirect,Model model) {
		System.out.println(redirect);
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping("/showRegister")
	public String showRegisterUI() {
		return "register";
	}
}
