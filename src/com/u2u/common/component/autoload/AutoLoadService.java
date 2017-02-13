package com.u2u.common.component.autoload;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * @ClassName: AutoLoadService <br>
 * @Description: Tomcat启动自启动类 <br>
 * @date 2015-3-18 下午1:09:55 <br>
 * 
 * @author Freud
 */
@Service("autoLoadService")
public class AutoLoadService {

	/**
	 * <p>
	 * Discription:[自启动方法]
	 * </p>
	 * 
	 * @author:[Freud]
	 * @update:[2015-3-18] [更改人姓名][变更描述]
	 */
	@PostConstruct
	public void autoLoad() {
		System.out.println("AutoLoadService---Generated");
	}

}
