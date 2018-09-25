package com.xueyi.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.xueyi.mapper.T_MALL_CLASS_1_mapper;

/**
 * 1、测试类,连接数据库
 * @author admin
 *
 */
public class TestClass1 {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		//1:加载全局配置文件
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//2:创建SqlSessionFactory
		sqlSessionFactory = new  SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test() throws Exception{
		//1:从sqlSessionFactory中获得sqlsession连接
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//2:获得T_MALL_CLASS_1_mapper实例对象
		T_MALL_CLASS_1_mapper class_1_mapper = sqlSession.getMapper(T_MALL_CLASS_1_mapper.class);
		
		//3:通过class_1_mapper去操作数据库
		List<T_MALL_CLASS_1_mapper> classList = class_1_mapper.getList();
		System.out.println(classList.size());
		
		//4:创建gson对象
		Gson gson = new Gson();
		
		//5:把数据转换成json对象
		String classstr = gson.toJson(classList);
		
		//6:生成静态的js文件
		FileOutputStream out = new FileOutputStream("e:/class_1.js");
		out.write(classstr.getBytes());
		out.close();
	}
}
