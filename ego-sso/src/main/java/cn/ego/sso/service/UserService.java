package cn.ego.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ego.utils.EgoResult;
import cn.ego.vo.UserVo;

/**
 * 用户操作接口
 * @author 起灬風了
 *
 */
public interface UserService {
	/**
	 * 检验用户信息是否可用
	 * @param param username phone email
	 * @param type 		 1		2	3
	 * @return
	 */
	EgoResult userCheck(String param,Integer type);

	EgoResult userLogin(UserVo userVo,HttpServletRequest request,HttpServletResponse response);

	EgoResult userToken(String token);

	EgoResult userRegister(UserVo userVo);

	EgoResult userLogout(String token);
}
