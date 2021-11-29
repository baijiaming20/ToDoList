package pers.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectUtils {

	private ObjectUtils() {}

	/**
	 * 获取对象参数属性名
	 */
	private static String[] getAttrNames(Field[] fields) {

		List<String> names = new LinkedList<>();

		for (Field field : fields) {
			names.add(field.getName());
		}

		return names.toArray(new String[0]);
	}

	/**
	 * 获取对象所有属性名
	 * @param obj 需要获取属性名的对象
	 * @return 对象属性名字符串数组
	 */
	public static String[] getAttrNames(Object obj) {
		return getAttrNames(obj.getClass().getDeclaredFields());
	}

	public static String[] getAttrNames(Class<?> c) {
		return getAttrNames(c.getDeclaredFields());
	}

	/**
	 * 获取对象所有公共属性名
	 */
	public static String[] getPublicAttrNames(Object obj) {
		return getAttrNames(obj.getClass().getFields());
	}

	public static String[] getPublicAttrNames(Class<?> c) {
		return getAttrNames(c.getFields());
	}

	/**
	 * 获取对象所有属性Map
	 */
	public static Map<String, Object> getAttrsMap(Object obj) {

		Class<?> objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();

		Map<String, Object> map = new HashMap<>();

		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据对象的属性名获取对象该属性的Field
	 */
	private static Field getField(Object obj, String key) {
		Class<?> objClass = obj.getClass();
		Field field = null;

		try {
			field = objClass.getDeclaredField(key);
			field.setAccessible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return field;
	}

	/**
	 * 设置对象属性值
	 */
	public static void setAttrValue(Object obj, String key, Object value) {

		try {
			getField(obj, key).set(obj, value);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取对象属性值
	 */
	public static Object getAttrValue(Object obj, String key) {

		Object value = null;

		try {
			value = getField(obj, key).get(obj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * 比较两个相同类型对象的属性值是否有不同
	 */
	public static <T> boolean compare(T obj1, T obj2) {
		return getAttrsMap(obj1).equals(getAttrsMap(obj2));
	}

	/**
	 * 克隆
	 * 需要无参构造函数
	 */
	public static <T> T clone(T obj) {
		Class<?> objClass = obj.getClass();
		T obj2 = null;

		try {
			obj2 = (T) objClass.getConstructor().newInstance();
			Map<String, Object> map = getAttrsMap(obj);

			for (String key : map.keySet()) {
				setAttrValue(obj2, key, map.get(key));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return obj2;
	}

	/**
	 * 将map转换为一个对象（需要无参构造函数）
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> c) {
		T t = null;
		try {
			t = c.getConstructor().newInstance();
			for (String key : map.keySet()) {
				setAttrValue(t, key, map.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

//	public static <T> List<T> mapToObject(List<Map<String, Object>> list, Class<T> c) {
//		List<T> tList = new LinkedList<>();
//		for (Map<String, Object> map : list) {
//			tList.add(mapToObject(map, c));
//		}
//		return tList;
//	}
	public static <T> List<T> mapToObject(List<Map<String, Object>> list, Class<T> c) {
		List<T> tList = new LinkedList<>();
		for (Map<String, Object> map : list) {
			tList.add(mapToObject(map, c));
		}
		return tList;
	}
}
