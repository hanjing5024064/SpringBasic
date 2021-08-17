package cn.mldn.mldnspring.util.redis;

import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;

public class DefaultRedisCache implements Cache {		// 自定义缓存实现
	private RedisTemplate<Long, Object> redisTemplate;	// 注入Redis操作模版
	private String name ;								// 定义缓存区名称
	@Override
	public String getName() {							// 获取缓存区名称
		return this.name;
	}

	@Override
	public Object getNativeCache() {					// 获取原生缓存配置
		return this.redisTemplate;
	}

	@Override
	public ValueWrapper get(Object key) {				// 根据key获取数据
		Object object = this.redisTemplate.opsForValue().get(key);
		ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
		return obj;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {		// 根据key获取数据并指明类型
		T object = (T) this.redisTemplate.opsForValue().get(key);
		return object ;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}

	@Override
	public void put(Object key, Object value) {			// 向缓存保存数据
		this.redisTemplate.opsForValue().set(Long.parseLong(key.toString()), value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	@Override
	public void evict(Object key) {						// 删除一个缓存数据
		this.redisTemplate.delete(Long.parseLong(key.toString())) ;
	}

	@Override
	public void clear() {								// 清空所有数据
		this.redisTemplate.getConnectionFactory().getConnection().flushDb(); 
	}
	public void setRedisTemplate(RedisTemplate<Long, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	public void setName(String name) {
		this.name = name;
	}
}
