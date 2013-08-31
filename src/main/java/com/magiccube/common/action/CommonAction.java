package com.magiccube.common.action;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.magiccube.common.util.EnvUtils;
import com.magiccube.core.base.action.BaseAction;
import com.magiccube.user.model.UserVO;

/**
 * 公共管理类
 * @author Administrator
 *
 */
@RemoteProxy
public class CommonAction extends BaseAction {
	/**
	 * 获取当前用户
	 * @return 当前用户
	 */
	@RemoteMethod
	public UserVO getUser(){
		return EnvUtils.getUser();
	}
}
