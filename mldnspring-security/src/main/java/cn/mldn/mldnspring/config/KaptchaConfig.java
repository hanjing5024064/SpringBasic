package cn.mldn.mldnspring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class KaptchaConfig {
	@Bean
	public DefaultKaptcha captchaProducer() {								// Kaptcha对象
		DefaultKaptcha captchaProducer = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", "yes");					// 设置边框
		properties.setProperty("kaptcha.border.color", "105,179,90");		// 设置颜色
		properties.setProperty("kaptcha.textproducer.font.color", "red");	// 字体颜色
		properties.setProperty("kaptcha.image.width", "125");				// 验证码宽度
		properties.setProperty("kaptcha.image.height", "45");				// 验证码高度 
		properties.setProperty("kaptcha.textproducer.font.size", "35");		// 文字大小
		properties.setProperty("kaptcha.session.key", "captcha");			// Session属性名称
		properties.setProperty("kaptcha.textproducer.char.length", "4");	// 长度
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");	// 字体
		Config config = new Config(properties);								// 定义配置类
		captchaProducer.setConfig(config);									// 保存配置
		return captchaProducer;
	}
}
