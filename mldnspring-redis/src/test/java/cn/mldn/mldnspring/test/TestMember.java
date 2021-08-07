package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.vo.Member;

@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)									// 使用Junit进行测试
public class TestMember {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate; 				// Redis操作模版
	@Test
	public void testSave() { 											// 数据保存处理
		Member vo = new Member();
		vo.setMid("mldn-java");
		vo.setBirthday(new Date());
		vo.setName("张三");
		vo.setAge(18);
		vo.setSalary(1.1);
		this.redisTemplate.opsForValue().set("mldn-1", vo);				// 保存对象
	}
	@Test
	public void testLoad() { 
		Object obj = this.redisTemplate.opsForValue().get("mldn-1") ;	// 要进行转型处理
		System.out.println(obj);										// 输出对象
	}
}
