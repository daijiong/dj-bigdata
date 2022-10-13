package org.djflying.bigdata.corejava.reflection;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Reflection tester
 *
 * @author daijiong
 * @version $Id: ReflectionTest.java, v 0.1 18-9-26 下午3:12 daijiong Exp $$
 */
@RunWith(JUnit4.class)
public class ReflectionTest {

    private Class personClass;

    @Before
    public void setUp() throws Exception {
        String className = "org.djflying.bigdata.corejava.reflection.Person";
        personClass = Class.forName(className);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 获取当前加载这个类的那个类加载器对象
     *
     * @throws Exception
     */
    @Test
    public void testGetClassloader() throws Exception {
        ClassLoader classLoader = personClass.getClassLoader();
        System.out.println(classLoader.toString());
    }

    /**
     * 打印这个类
     *
     * @throws Exception
     */
    @Test
    public void testPrintClass() throws Exception {
        System.out.println(personClass);
        System.out.println(Person.class);
    }

    /**
     * 通过这个类创建一个实例对象，并打印实例的toString方法。
     * 
     * @throws Exception
     */
    @Test
    public void testNewInstance() throws Exception {
        Object person = personClass.newInstance();
        System.out.println(person);
    }

    /**
     * 获取这个类的非私有的构造函数
     * 
     * @throws Exception
     */
    @Test
    public void testGetConstructor() throws Exception {
        Constructor constructor = personClass.getConstructor(String.class, String.class);
        System.out.println(constructor.toString());
    }

    /**
     * 获取这个类的私有的构造函数
     * 
     * @throws Exception
     */
    @Test
    public void testGetDeclaredConstructor() throws Exception {
        Constructor declaredConstructor = personClass.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor.toString());
    }

    /**
     * 根据这个构造函数创建实例，并打印实例的toString方法。
     * 
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        Constructor constructor = personClass.getConstructor(String.class, String.class);
        Object person = constructor.newInstance("david", "suzhou");
        System.out.println(person);

        Constructor declaredConstructor = personClass.getDeclaredConstructor(String.class);
        // 强制取消Java的权限检测，不然会报错
        declaredConstructor.setAccessible(true);
        Object person2 = declaredConstructor.newInstance("david");
        System.out.println(person2);
    }

    /**
     * 访问非私有的成员变量
     * 
     * @throws Exception
     */
    @Test
    public void testGetField() throws Exception {
        Field field = personClass.getField("address");
        Object person = personClass.getConstructor().newInstance();
        field.set(person, "suzhou");
        System.out.println(field.get(person));
    }

    /**
     * 访问私有的成员变量
     * 
     * @throws Exception
     */
    @Test
    public void testGetDeclaredField() throws Exception {
        Field declaredField = personClass.getDeclaredField("name");
        // 强制取消Java的权限检测，不然会报错
        declaredField.setAccessible(true);
        Object person = personClass.getConstructor().newInstance();
        declaredField.set(person, "david");
        System.out.println(declaredField.get(person));
    }

    /**
     * 获取非私有的成员函数
     *
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        Method method = personClass.getMethod("toString");
        Object person = personClass.getConstructor().newInstance();
        Object invoke = method.invoke(person);
        System.out.println(invoke);
    }

    /**
     * 获取私有的成员函数
     * 
     * @throws Exception
     */
    @Test
    public void testGetDeclaredMethod() throws Exception {
        Method declaredMethod = personClass.getDeclaredMethod("testPrivate");
        // 强制取消Java的权限检测，不然会报错
        declaredMethod.setAccessible(true);
        Object person = personClass.getConstructor().newInstance();
        Object invoke = declaredMethod.invoke(person);
        System.out.println(invoke);
    }

    /**
     * 获取某个类实现的所有接口
     * 
     * @throws Exception
     */
    @Test
    public void testGetInterface() throws Exception {
        Class[] interfaces = personClass.getInterfaces();
        for (Class intf : interfaces) {
            System.out.println(intf);
        }
    }

    /**
     * 反射当前这个类的直接父类
     *
     * @throws Exception
     */
    @Test
    public void testGetGenericSuperclass() throws Exception {
        Type genericSuperclass = personClass.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /**
     * 判断当前的Class对象表示是否是数组
     * 
     * @throws Exception
     */
    @Test
    public void testIsArray() throws Exception {
        boolean isArray = personClass.isArray();
        System.out.println(isArray);
    }

    /**
     * 判断当前的Class对象表示是否是枚举类
     * 
     * @throws Exception
     */
    @Test
    public void testIsEnum() throws Exception {
        boolean isEnum = personClass.isEnum();
        System.out.println(isEnum);
    }

    /**
     * 判断当前的Class对象表示是否是接口
     * 
     * @throws Exception
     */
    @Test
    public void testIsInterface() throws Exception {
        boolean isInterface = personClass.isInterface();
        System.out.println(isInterface);
    }

    /**
     * 使用getResourceAsStream加载资源文件
     * 
     * @throws Exception
     */
    @Test
    public void testGetResourceAsStream() throws Exception {
        InputStream resourceAsStream = personClass.getResourceAsStream("log4j.properties");

    }
}
