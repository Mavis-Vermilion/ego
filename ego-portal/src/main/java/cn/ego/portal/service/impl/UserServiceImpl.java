package cn.ego.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.portal.service.UserService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.UserVo;
@Service
public class UserServiceImpl implements UserService{

	@Value("${SSO_URL}")
	private String SSO_URL;
	@Value("${SSO_CHECK_TOKEN_ADDRESS}")
	private String SSO_CHECK_TOKEN_ADDRESS;
	
	
	
	@Override
	public UserVo findUserByToken(String token) {
		if(StringUtils.isBlank(token))
			return null;
		String userJSON = HttpClientUtil.doGet(SSO_URL+SSO_CHECK_TOKEN_ADDRESS+token);
		if(StringUtils.isEmpty(userJSON))
			return null;
		EgoResult result = JsonUtils.jsonToPojo(userJSON, EgoResult.class);
		if(result.getStatus()!=200)
			return null;
		EgoResult egoResult = EgoResult.formatToPojo(userJSON, UserVo.class);
		return (UserVo) egoResult.getData();
	}

}
