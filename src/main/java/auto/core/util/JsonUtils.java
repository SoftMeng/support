package auto.core.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 〈json字符串处理〉
 * 〈json字符串和java对象的相互转换〉
 * @author mengxy[孟祥元]
 * @version 2015年12月16日
 * @see JsonUtils
 * @since 1.0
 */
@SuppressWarnings({ "unchecked", "deprecation" })
public class JsonUtils {
	private ObjectMapper				mapper		= null;
	private static volatile JsonUtils	_instance	= null;

	// default constructor
	private JsonUtils() {
		this.mapper = new ObjectMapper();
	}

	/**
	 * Description: 单例<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @return 
	 * @see 
	 */
	public static JsonUtils getInstance() {
		if (_instance == null) {
			synchronized (JsonUtils.class) {
				if (_instance == null) {
					_instance = new JsonUtils();
				}
			}
		}
		return _instance;
	}

	/**
	 * Description: java对象转换成json字符串<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param object
	 * @return 
	 * @see 
	 */
	public String toJson(Object object) {
		try {
			return this.mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Description:json的List形式的字符串转换成java对象的List <br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param json
	 * @param collectionType
	 * @param targetType
	 * @return 
	 * @see 
	 */
	public <T> T toListObject(String json, Class<T> collectionType, Class<?> targetType) {
		JavaType javaType = this.mapper.getTypeFactory().constructParametricType(collectionType, targetType);;
		try {
			return (T) this.mapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Description: json字符串转换成java对象<br>
	 * 1、…<br>
	 * 2、…<br>
	 * Implement: <br>
	 * 1、…<br>
	 * 2、…<br>
	 * 
	 * @param json
	 * @param targetType
	 * @return 
	 * @see 
	 */
	public <T> T toObject(String json, Class<T> targetType) {
		try {
			return (T) this.mapper.readValue(json, targetType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ObjectMapper getMapper() {
		return mapper;
	}
}
