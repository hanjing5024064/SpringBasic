package cn.mldn.mldnspring.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.mldn.mldnspring.util.dbc.RedisConnectionUtil;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;

public class TestRedisDataDemo {
	public static JedisCluster jedis = null ;				// 保存Jedis连接
	static {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		jedis = rcu.getConnection() ;				// 获取Jedis连接对象
	}
	@Test
	public void testStringData() throws Exception {
		jedis.set("mldn", "Java") ;						// 设置数据
		jedis.setex("mldn-message",3, "helloworld") ;	// 设置数据，3秒后失效
		TimeUnit.SECONDS.sleep(4);						// 延迟4秒执行
		System.out.println(jedis.get("mldn"));			// 可以获取数据 			
		System.out.println(jedis.get("mldn-message")); 	// 无法获取数据
	}	
	@Test
	public void testGEOData() throws Exception {
		jedis.flushDB() ;									// 清空数据库
		Map<String,GeoCoordinate> pointsMap = new HashMap<String,GeoCoordinate>() ;	// 保存坐标
		pointsMap.put("天安门",new GeoCoordinate(116.403963, 39.915119)) ;	// 添加坐标
		pointsMap.put("王府井",new GeoCoordinate(116.417876, 39.915411)) ;	// 添加坐标
		pointsMap.put("前门大街",new GeoCoordinate(116.404354, 39.904748)) ;	// 添加坐标
		jedis.geoadd("point", pointsMap) ;					// 保存坐标信息
		// 查找距离当前坐标周围1000M的建筑物信息
		List<GeoRadiusResponse> georadius = jedis.georadius("point", 116.415901, 39.914805, 1000, GeoUnit.M,GeoRadiusParam.geoRadiusParam().withDist().count(1));
		georadius.forEach((geoData)->{
			System.out.println("建筑物名称：" + geoData.getMemberByString() + "、距离：" + geoData.getDistance());
		});													// 迭代输出
	}
	
	@Test
	public void testListData() throws Exception {
		jedis.flushDB() ;									// 清空数据库
		jedis.lpush("user-mldn", "mldnjava","jixianit") ;	// 设置数据
		jedis.rpush("user-mldn", "hello","world") ;			// 设置数据
		System.out.print(jedis.rpop("user-mldn") + "、");		// 从队列头部弹出一个数据
		System.out.print(jedis.rpop("user-mldn") + "、");		// 从队列头部弹出一个数据
		System.out.print(jedis.lpop("user-mldn") + "、");		// 从队列尾部弹出一个数据
		System.out.print(jedis.lpop("user-mldn") + "、");		// 从队列尾部弹出一个数据
		System.out.print(jedis.lpop("user-mldn"));		// 没有数据，返回null
	}

	@Test
	public void testListDataGet() throws Exception {
		jedis.flushDB() ;									// 清空数据库
		jedis.lpush("user-mldn", "mldnjava","jixianit") ;	// 设置数据
		jedis.rpush("user-mldn", "hello","world") ;			// 设置数据
		List<String> all = jedis.lrange("user-mldn", 0, -1) ;
		all.forEach((data)->{
			System.out.print(data + "、") ;});	// 迭代输出
	}
	
	@Test
	public void testSetData() throws Exception {
		jedis.flushDB() ;											// 清空数据库
		jedis.sadd("user-admin", "a", "b", "c", "d", "e");			// 设置数据
		jedis.sadd("user-mldn", "a", "c", "e", "x", "y", "z");		// 设置数据
		Set<String> all = jedis.sinter("user-admin","user-mldn") ;	// 交集计算
		all.forEach((data)->{
			System.out.print(data + "、") ;});					// 迭代输出
	}

	@Test
	public void testZSetData() throws Exception {
		jedis.flushDB() ;											// 清空数据库
		Map<String,Double> map = new HashMap<String,Double>() ;		// 设置Map集合保存数据
		map.put("pid-1-1", 2.0) ;									// 保存数据与分数
		map.put("pid-1-2", 1.0) ;									// 保存数据与分数
		map.put("pid-2-1", 5.0) ;									// 保存数据与分数
		jedis.zadd("user-mldn", map) ;								// 将数据保存到Redis之中
		// 根据分数范围获取全部数据内容与分数，此时利用Tuple保存每一组结果
		Set<Tuple> all = jedis.zrangeByScoreWithScores("user-mldn", 1.0, 5.0) ;
		all.forEach((data) -> {
			System.out.println("元素名称：" + data.getElement() 
					+ "、分数：" + data.getScore());					// 输出数据
		}); 														// 迭代输出
	}
	
	@Test
	public void testHashData() throws Exception {
		jedis.hset("user-mldn", "name", "李兴华") ;				// 设置hash数据与属性KEY
		jedis.hset("user-mldn", "age", String.valueOf(18)) ; 	// 设置hash数据与属性KEY
		jedis.hset("user-mldn", "sex", "男") ;					// 设置hash数据与属性KEY
		System.out.println(jedis.hget("user-mldn", "name"));	// 获取指定属性KEY数据
	}

	


}
