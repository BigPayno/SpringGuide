package lifecycle;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.ClassPathResource;

/**
 * @author payno
 * @date 2019/11/26 15:16
 * @description
 * 1. 调用SqlSessionFactoryBuilder对象的build(inputStream)方法；
 * 2. SqlSessionFactoryBuilder会根据输入流inputStream等信息创建XMLConfigBuilder对象;
 * 3. SqlSessionFactoryBuilder调用XMLConfigBuilder对象的parse()方法；
 * 4. XMLConfigBuilder对象返回Configuration对象；
 * 5. SqlSessionFactoryBuilder根据Configuration对象创建一个DefaultSessionFactory对象；
 * 6. SqlSessionFactoryBuilder返回 DefaultSessionFactory对象给Client，供Client使用。
 */
public class Init {
    public static void main(String[] args) throws Exception{
        ClassPathResource resource=new ClassPathResource("init.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder()
                .build(resource.getInputStream());
        SqlSession session=sessionFactory.openSession();
        session.selectList("");
    }
}
