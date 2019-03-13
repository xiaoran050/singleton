package com.gupao.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Spring中的做法，就是用这种注册式单例
//优点:将每一个实例都缓存到一个容器中,使用唯一标识获取实例
//缺点:存在线程安全问题
public class ContainerSingleton {
    private ContainerSingleton(){}
    private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();
    public static Object getInstance(String className){
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}
