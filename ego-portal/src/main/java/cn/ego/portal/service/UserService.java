package cn.ego.portal.service;

import cn.ego.vo.UserVo;

public interface UserService {

	public UserVo findUserByToken(String token);
}
