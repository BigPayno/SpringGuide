package com.payno.springguide.spring;

import com.google.common.io.ByteStreams;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import org.springframework.core.io.*;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/18 08:43
 * @description
 * Resource框架
 *  Resource     ->FileSystemResource
 *               ->ClassPathResource
 *               ->UrlResource
 *               ->BeanDefinitionResource
 *               ...
 *  DefaultResourceLoader extends ResourceLoader
 *      Resource ->FileSystemResource
 *               ->ClassPathResource
 *               ->UrlResource
 *               ->BeanDefinitionResource
 *               ...
 *  DefaultResourceLoader extends ResourceLoader
 *      <>-->Set<ProtocolResolver> Visitors 一堆访问者，提供对Loader的访问
 *        -->Map<Class<?>, Map<Resource, ?>> resourceCaches   ConcurrentHashMap 资源缓存
 *        -->ClassLoader 类加载器
 * Resolver(location,this) 是一个Functional接口，使用Visitor模式 String,Loader->Resource
 * ClassLoader来自ClassUtils
 * ResourceUtils是个非常有用的工具类
 */
public class ResourceFrameworkGuide {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader=ClassUtils.getDefaultClassLoader();
        DefaultResourceLoader defaultResourceLoader=new DefaultResourceLoader();
        defaultResourceLoader.addProtocolResolver((path,loader)->{
            Resource resource=null;
            System.out.printf("%s 加载 %s 路径的Resource，并放入缓存ConcurrentHashMap中",path,loader.getClass());
            try {
                if(path.startsWith("classpath:")){
                    resource=new ClassPathResource(path.substring("classpath:".length()), loader.getClassLoader());
                }else if (path.startsWith("/")){
                    Invokable<DefaultResourceLoader,Resource> getResourceByPath=new TypeToken<DefaultResourceLoader>(){}.method(
                            DefaultResourceLoader.class.getMethod("getResourceByPath", String.class))
                            .returning(Resource.class);
                    getResourceByPath.setAccessible(true);
                    resource=getResourceByPath.invoke((DefaultResourceLoader) loader,path);
                }else {
                    try {
                        URL url = new URL(path);
                        return (Resource)(ResourceUtils.isFileURL(url) ? new FileUrlResource(url) : new UrlResource(url));
                    } catch (MalformedURLException var5) {
                        var5.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return resource;
        });
        Resource fileResource=(Resource) defaultResourceLoader.getResource("file:d:/test.json");
        System.out.println(new String(ByteStreams.toByteArray(fileResource.getInputStream()), StandardCharsets.UTF_8));
    }

    public static class ResolveList{
        @FunctionalInterface
        public static interface Resolve{
            String apply(String str,ResolveList resolveList);
        }
        public ResolveList(int capacity){
            resolves=new ArrayList<>();
            resources=new ArrayList<>(capacity);
        }
        private List<Resolve> resolves;
        private List<String> resources;
        public String getString(String string){
            Iterator<Resolve> iterator=resolves.iterator();
            while(iterator.hasNext()){
                string =iterator.next().apply(string,this);
            }
            return string;
        }
        public void addResolve(Resolve resolve){
            resolves.add(resolve);
        }
        public void clear(){
            resources.clear();
        }
    }
}
