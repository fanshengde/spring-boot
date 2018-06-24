package com.fsd.password.demo;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

import com.fsd.base.BaseTest;

public class MyRealmTest extends BaseTest {
	// @Test
	public void testPasswordServiceWithMyRealm() {
		login("classpath:password/password.ini", "zhang", "123");
	}

	// @Test
	public void testPasswordServiceWithMyRealm4Jdbc() {
		login("classpath:password/password-jdbc.ini", "zhang", "123");
	}

	/**
	 * fsd-未成功
	 * 
	 * */
	@Test
	public void testHashedCredentialsMatcheWithJdbcRealm() {
	    BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);  
		
		login("classpath:password/shiro-jdbc-hash.ini", "liu", "123");
	}

	
	public class EnumConverter extends AbstractConverter {

		@Override
		protected String convertToString(final Object value) throws Throwable {
			return ((Enum) value).name();
		}

		@Override
		protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
			return (T) Enum.valueOf((Class) type, value.toString());
		}

		@Override
		protected Class<?> getDefaultType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
