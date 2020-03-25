import dto.Check4Dto;
import dto.Check4Vo;
import entity.Check4;
import mapper.Check4Mapper;
import mapper.Check4Mapper2;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

/**
 * @author payno
 * @date 2020/3/25 09:13
 * @description
 *      可以在generated sources看到生成的MapperImpl
 */
public class Check4Test {
    /**
     * SimpleConvert
     */
    @Test
    public void test1(){
        Check4 check4=Check4.builder().cusName("payno")
                .idCard("320723199611193816")
                .mobile("13270851790")
                .channel("005").build();
        Check4Dto dto = Mappers.getMapper(Check4Mapper.class)
                .dtoFrom(check4);
        Check4Vo vo = Mappers.getMapper(Check4Mapper.class)
                .voFrom(check4);
        System.out.println(dto);
        System.out.println(vo);
        Check4 check4FromDto = Mappers.getMapper(Check4Mapper.class).ofDto(dto);
        System.out.println(check4FromDto);
    }

    /**
     * ConvertWithDiffNames
     */
    @Test
    public void test2(){
        Check4 check4=Check4.builder().cusName("payno")
                .idCard("320723199611193816")
                .mobile("13270851790")
                .channel("005").build();
        Check4Dto dto = Mappers.getMapper(Check4Mapper2.class)
                .dtoFrom(check4);
        System.out.println(dto);
    }


}
