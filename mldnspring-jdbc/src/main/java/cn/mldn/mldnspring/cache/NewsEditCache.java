package cn.mldn.mldnspring.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
@Caching(put= {
		@CachePut(key = "#vo.nid",unless="#result==null"),
		@CachePut(key = "#vo.title",unless="#result==null")
})
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NewsEditCache {

}
