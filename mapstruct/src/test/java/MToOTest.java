import dto.Check4DetailVo;
import entity.Check4;
import entity.Check4Detail;
import mapper.Check4Mapper3;
import org.junit.Test;

/**
 * @author payno
 * @date 2020/3/25 09:55
 * @description
 */
public class MToOTest {
    @Test
    public void test(){
        Check4 check4=Check4.builder().cusName("DEAN").build();
        Check4Detail detail=new Check4Detail();
        detail.setDetail("hello,i like MDIL");
        Check4DetailVo vo= Check4Mapper3.MAPPER.of(check4,detail);
        System.out.println(vo);
    }
}
