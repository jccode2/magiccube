
package com.magiccube.core.util.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.magiccube.user.model.UserVO;

/**
 * DataGenerator!
 * 
 * @author Xingling Chen
 * @since JDK1.6
 * @history 2011-05-11 Xingling build
 */
public class DataGenerator {
    
    private DataGenerator() {
        
    }
    
    public static Object get(Class<? extends Object> clazz) {
        return get(clazz, 1);
    }
    
    //
    // Usage:
    // Demo1 : User user = DataGenerator.get(User.class);
    // Then : user.getId() is 1 and user.getName() is name1
    // Demo2 : User user = DataGenerator.get(User.class,100);
    // Then : user.getId() is 100 and user.getName() is name100
    //
    public static Object get(Class<? extends Object> clazz, int suffix) {
        Object result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            try {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    String field =
                        methodName.substring(methodName.indexOf("set") + "set".length(), methodName.length())
                            .toLowerCase();
                    Class<?>[] paramTypes = method.getParameterTypes();
                    Object[] paramValues = new Object[paramTypes.length];
                    for (int i = 0; i < paramTypes.length; i++) {
                        if (paramTypes[i] == String.class) {
                            paramValues[i] = fillAsString(field, suffix);
                        } else if (paramTypes[i] == double.class || paramTypes[i] == Double.class) {
                            paramValues[i] = fillAsDouble(field, suffix);
                        } else if (paramTypes[i] == int.class || paramTypes[i] == Integer.class) {
                            paramValues[i] = fillAsInt(field, suffix);
                        }else if (paramTypes[i] == boolean.class || paramTypes[i] == Boolean.class) {
                            paramValues[i] = fillAsBoolean(field, suffix);
                        }
                    }
                    method.invoke(result, paramValues);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    private static String fillAsString(String field, int suffix) {
        String result = field;
        result += String.valueOf(suffix);
        return result;
    }
    
    private static Double fillAsDouble(String field, int suffix) {
        String strResult = String.valueOf(suffix);
        strResult += String.valueOf(Math.random()).substring(1, 4);
        Double result = Double.valueOf(strResult);
        return result;
    }
    
    private static Integer fillAsInt(String field, int suffix) {
        return suffix;
    }
    
    private static Boolean fillAsBoolean(String field, int suffix) {
        return false;
    }
    
    public static List<Object> getList(Class<? extends Object> clazz, int count) {
        
        return getList(clazz, count, 1);
    }
    
    //
    // Usage:
    // Demo1 : List<Object> userList = DataGenerator.getList(User.class,100);
    // Then : userList.size() is 100 and (User)userList.get(0) is an User, and it's name is name1.
    // Demo2 : User user = get(User.class,100,200);
    // Then : userList.size() is 100 and (User)userList.get(0) is an User, and it's name is name200.
    //
    public static List<Object> getList(Class<? extends Object> clazz, int count, int suffix) {
        List<Object> result = new LinkedList<Object>();
        for (int i = 0; i < count; i++) {
            result.add(get(clazz, suffix));
            suffix++;
        }
        return result;
    }
    
    public static void main(String[] args) {
        UserVO user = (UserVO) DataGenerator.get(UserVO.class, 100);
        System.out.println("Demo: UserVO user = (UserVO) DataGenerator.get(UserVO.class, 100);");
        System.out.println("user.getEmail():" + user.getEmail());
        System.out.println("");
        List<Object> userList = DataGenerator.getList(UserVO.class, 100);
        System.out.println("Demo: List<Object> userList = DataGenerator.getList(UserVO.class, 100);");
        System.out.println("userList.getSize():" + userList.size());
        System.out.println("((UserVO) userList.get(50)).getUserName():" +((UserVO) userList.get(50)).getUserName());
    }
    
}
