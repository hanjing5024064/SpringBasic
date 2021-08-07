package cn.mldn.mldnspring.test;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.IRedEnvelopeService;

@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)								// 使用Junit进行测试
public class TestRedEnvelope {
	@Autowired
	private IRedEnvelopeService redEnvelopeService; 				// Redis操作模版
	@Test
	public void testSave() { 	// 保存红包信息
		String key = this.redEnvelopeService.add("mldn",5, 66.66) ;
		System.out.println(key);
	}
	@Test
	public void testGrab() throws Exception {  
		String key = "envelope-mldn-1628324140351" ;
		CountDownLatch latch = new CountDownLatch(10) ;		// 线程阻塞处理
		for (int x = 0; x < 10; x++) {
			new Thread(()->{ 								// 使用线程名称作为用户ID
				String userid = Thread.currentThread().getName() ;
				Double result = this.redEnvelopeService.grab(userid, key) ;
				if (result != null) { 
					System.out.println(userid + "抢到红包，金额：" + result);
				} else {
					System.out.println(userid + "没有抢到红包！");
				}
				latch.countDown();							// 减少等待线程量
			},"MLDN用户-" + x) .start(); 
		}
		latch.await(); 										// 等到全部抢完后输出信息
		System.out.println("*** 红包已全部抢完，最终结果：");
		this.redEnvelopeService.result("result-" + key).forEach((Object k,Object v)->{
			System.out.println("\t|- " + k + "抢到红包，金额：" + v);
		});
	}
}
