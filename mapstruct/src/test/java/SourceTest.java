import entity.Source;
import entity.SubSource;
import entity.Target;
import mapper.SourceMapper;
import mapper.SourceMapper2;
import org.junit.Test;

/**
 * @author payno
 * @date 2020/3/25 09:38
 * @description
 */
public class SourceTest {
    SubSource subSource=SubSource.builder().deleted(0).name("abc").build();
    Source source=Source.builder().subSource(subSource).id("001").num(66).totalCount(23).build();

    /**
     *  每个Mapper的转换逻辑
     *  比如Source中的subSource转换Target的subTarget会默认在Mapper接口中寻找
     *  入参是subSource，返回是subTarget的方法
     *  同理，就是result和deleted的关系
     */
    @Test
    public void test(){
        Target target=SourceMapper.INSTANCE.from(source);
        System.out.println(target);
        Target target1= SourceMapper2.INSTANCE.from(source);
        System.out.println(target1);
    }
}
