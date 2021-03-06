package ok;

import java.lang.reflect.InvocationTargetException;

import ok.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest {

	/**
	 * 测试通过BeanUtils进行对象的字段操作
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testGetProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object object = new Student();
		System.out.println(object); 
		
		BeanUtils.setProperty(object, "idCard", "211121196509091876");
		System.out.println(object); 
		
		Object val = BeanUtils.getProperty(object, "idCard");
		System.out.println(val);
	}
	
	@Test
	public void testSetProperty() throws IllegalAccessException, InvocationTargetException {
		
		Object object = new Student();
		System.out.println(object); 
		
		BeanUtils.setProperty(object, "idCard2", "211121196509091876");
		System.out.println(object); 
		
	}

}
