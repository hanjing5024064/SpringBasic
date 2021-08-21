package cn.mldn.mldnspring.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * 定义一个用于操作JPA的工厂程序类，该类的主要功能是负责
 * EntityManger与EntityManagerFactory接口的对象管理
 * @author 李兴华
 */
public class JPAEntityFactory {
	private static final String PERSISTENCE_UNIT = "MLDNJPA" ;	// 持久化单元名称
	private static EntityManagerFactory entityManagerFactory ; 	// 定义连接工厂类
	private static ThreadLocal<EntityManager> entityThreadLocal = 
			new ThreadLocal<EntityManager>() ;					// 保存EntityManager接口对象
	static {													// 静态代码块获取EntityManagerFactory实例
		rebuildEntityManagerFactory() ; 						// 实例化EntityManagerFactory接口对象
	}
	private JPAEntityFactory() {}								// 将构造方法私有化，通过静态方法获取相应对象
	/**
	 * 获取EntityManagerFactory接口对象
	 * @return EntityManagerFactory接口实例
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {						// 没有连接工厂
			rebuildEntityManagerFactory() ; 					// 创建工厂实例
		}
		return entityManagerFactory ; 							// 返回连接工厂接口对象 
	}
	/**
	 * 获取EntityManager对象，不同的线程获取各自的EntityManager接口对象
	 * @return EntityManager接口实例
	 */
	public static EntityManager getEntityManager() {
		EntityManager entityManager = entityThreadLocal.get() ;	// 获取EntityManager接口对象
		if (entityManager == null) {							// 如果没有实例化对象
			if (entityManagerFactory == null) {					// 没有连接工厂
				rebuildEntityManagerFactory() ;					// 创建工厂实例
			}
			entityManager = entityManagerFactory.createEntityManager() ; // 创建新的EntityManager接口对象
			entityThreadLocal.set(entityManager); 				// 保存对象信息
		}
		return entityManager ;
	}
	/**
	 * 关闭EntityManager连接
	 */
	public static void close() {
		EntityManager entityManager = entityThreadLocal.get() ;
		if (entityManager != null) {							// 已保存有EntityManager
			entityManager.close(); 
			entityThreadLocal.remove(); 						// 从ThreadLocal之中删除对象
		}
	}
	/**
	 * 该方法的主要功能是获取EntityManagerFactory接口对象
	 */
	private static void rebuildEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT) ;
	}
}
