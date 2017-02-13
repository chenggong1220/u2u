package com.u2u.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @ClassName: ReflectionUtils <br>
 * @Description: 反射工具类 <br>
 * @date 2015-1-15 上午11:41:40 <br>
 * 
 * @author Dean
 */
public class ReflectionUtils
{
    
    private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
    
    static
    {
        final DateConverter dc = new DateConverter();
        dc.setUseLocaleFormat(true);
        dc.setPatterns(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
        ConvertUtils.register(dc, Date.class);
    }
    
    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成List.
     * 
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List convertElementPropertyToList(final Collection collection, final String propertyName)
    {
        final List list = new ArrayList();
        
        try
        {
            for (final Object obj : collection)
            {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        }
        catch (final Exception e)
        {
            throw convertReflectionExceptionToUnchecked(e);
        }
        
        return list;
    }
    
    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
     * 
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator 分隔符.
     */
    @SuppressWarnings("rawtypes")
    public static String convertElementPropertyToString(final Collection collection, final String propertyName,
        final String separator)
    {
        final List list = convertElementPropertyToList(collection, propertyName);
        return org.apache.commons.lang.StringUtils.join(list, separator);
    }
    
    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(final Exception e)
    {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
            || e instanceof NoSuchMethodException)
        {
            return new IllegalArgumentException("Reflection Exception.", e);
        }
        else if (e instanceof InvocationTargetException)
        {
            return new RuntimeException("Reflection Exception.", ((InvocationTargetException)e).getTargetException());
        }
        else if (e instanceof RuntimeException)
        {
            return (RuntimeException)e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }
    
    /**
     * 转换字符串到相应类型.
     * 
     * @param value 待转换的字符串
     * @param toType 转换目标类型
     */
    public static Object convertStringToObject(final String value, final Class<?> toType)
    {
        try
        {
            return ConvertUtils.convert(value, toType);
        }
        catch (final Exception e)
        {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 循环向上转型, 获取对象的DeclaredField.
     * 
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getDeclaredField(final Object object, final String fieldName)
    {
        Assert.notNull(object, "object不能为空");
        Assert.hasText(fieldName, "fieldName");
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass =
            superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(fieldName);
            }
            catch (final NoSuchFieldException e)
            {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }
    
    /**
     * 循环向上转型,获取对象的DeclaredMethod.
     * 
     * 如向上转型到Object仍无法找到, 返回null.
     */
    protected static Method getDeclaredMethod(final Object object, final String methodName,
        final Class<?>[] parameterTypes)
    {
        Assert.notNull(object, "object不能为空");
        
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass =
            superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            }
            catch (final NoSuchMethodException e)
            {
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }
    
    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName)
    {
        final Field field = getDeclaredField(object, fieldName);
        
        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        
        makeAccessible(field);
        
        Object result = null;
        try
        {
            result = field.get(object);
        }
        catch (final IllegalAccessException e)
        {
            logger.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }
    
    /**
     * 通过反射,获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao extends HibernateDao<User>
     * 
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be determined
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Class<T> getSuperClassGenricType(final Class clazz)
    {
        return getSuperClassGenricType(clazz, 0);
    }
    
    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * 
     * 如public UserDao extends HibernateDao<User,Long>
     * 
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    @SuppressWarnings("rawtypes")
    public static Class getSuperClassGenricType(final Class clazz, final int index)
    {
        
        final Type genType = clazz.getGenericSuperclass();
        
        if (!(genType instanceof ParameterizedType))
        {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        
        final Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        
        if (index >= params.length || index < 0)
        {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class))
        {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        
        return (Class)params[index];
    }
    
    /**
     * <p>
     * Discription:[调用Getter方法.]
     * </p>
     * 
     * @param target
     * @param propertyName
     * @return
     * @author:[Dean]
     */
    public static Object invokeGetterMethod(final Object target, final String propertyName)
    {
        final String getterMethodName = "get" + org.apache.commons.lang.StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
    }
    
    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     */
    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
        final Object[] parameters)
    {
        final Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null)
        {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }
        
        method.setAccessible(true);
        
        try
        {
            return method.invoke(object, parameters);
        }
        catch (final Exception e)
        {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     */
    public static void invokeSetterMethod(final Object target, final String propertyName, final Object value)
    {
        invokeSetterMethod(target, propertyName, value, null);
    }
    
    /**
     * 调用Setter方法.
     * 
     * @propertyType 用于查找Setter方法,为空时使用value的Class替代.
     */
    public static void invokeSetterMethod(final Object target, final String propertyName, final Object value,
        final Class<?> propertyType)
    {
        final Class<?> type = propertyType != null ? propertyType : value.getClass();
        final String setterMethodName = "set" + org.apache.commons.lang.StringUtils.capitalize(propertyName);
        invokeMethod(target, setterMethodName, new Class[] {type}, new Object[] {value});
    }
    
    public static void main(final String args[])
    {
        // ResBook a = new ResBook();
        // ReflectionUtils.invokeSetterMethod(a, "nameCn", "123");
        // log.info(ReflectionUtils.invokeGetterMethod(a, "nameCn").toString());
    }
    
    /**
     * 强行设置Field可访问.
     */
    protected static void makeAccessible(final Field field)
    {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()))
        {
            field.setAccessible(true);
        }
    }
    
    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value)
    {
        final Field field = getDeclaredField(object, fieldName);
        
        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        
        makeAccessible(field);
        
        try
        {
            field.set(object, value);
        }
        catch (final IllegalAccessException e)
        {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }
    }
}
