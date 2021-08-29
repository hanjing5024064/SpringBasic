package cn.mldn.mldnspring.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class IPAddressVoter implements AccessDecisionVoter<Object> {
	private static final String LOCAL_FLAG = "LOCAL_IP";		// 需要判断的访问标记 

	@Override 
	public boolean supports(ConfigAttribute attribute) { 		// 如果发现有指定配置属性则执行投票
		return attribute != null
				&& attribute.toString().contains(LOCAL_FLAG);
	} 

	@Override
	public boolean supports(Class<?> clazz) {					// 对所有访问类均支持投票
		return true; 
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		if (!(authentication.getDetails() instanceof WebAuthenticationDetails)) {	// 如果不是来自WEB访问
			return AccessDecisionVoter.ACCESS_DENIED;			// 拒绝该用户访问
		}
		// 通过认证信息获取用户的详情内容，该内容类型为“WebAuthenticationDetails”
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		String ip = details.getRemoteAddress() ;				// 取得当前操作的IP地址
		Iterator<ConfigAttribute> iter = attributes.iterator() ;	// 获取每一个配置属性
		while (iter.hasNext()) {								// 循环每一个配置属性
			ConfigAttribute ca = iter.next() ;					// 获取属性
			if (ca.toString().contains(LOCAL_FLAG)) {			// 如果在本地执行
				if ("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) {	// 本机访问
					return AccessDecisionVoter.ACCESS_GRANTED ; // 访问通过
				}
			}
		}
		return AccessDecisionVoter.ACCESS_ABSTAIN ;				// 弃权不参与投票
	}

}
