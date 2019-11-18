package com.payno.springguide.spring;

import com.google.common.io.ByteStreams;
import org.springframework.core.OverridingClassLoader;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author payno
 * @date 2019/11/18 15:42
 * @description
 */
public final class ResourceClassLoader extends ClassLoader{
    final ClassLoader parent;
    public ResourceClassLoader(){
        this.parent=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
    }
    public ResourceClassLoader(ClassLoader parent){
        this.parent=parent;
    }
    private DefaultResourceLoader resourceLoader=new DefaultResourceLoader();
    private Map<String,String> resourceMap=new ConcurrentHashMap<>(16);
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        if(resourceMap.containsKey(name)){
            try(InputStream io=resourceLoader.getResource(resourceMap.get(name)).getInputStream()){
                byte[] resource= ByteStreams.toByteArray(io);
                return defineClass(name,resource,0,resource.length);
            }catch (IOException e){
                throw new ClassNotFoundException("the resource io occurs exception!");
            }
        }else {
            return getParent().loadClass(name);
        }
    }

    public void resolve(String name,String resource){
        resourceMap.put(name,resource);
    }

    public void clear(){
        resourceMap.clear();
    }
}
