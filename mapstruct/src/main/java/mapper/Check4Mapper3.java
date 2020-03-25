package mapper;

import dto.Check4DetailVo;
import entity.Check4;
import entity.Check4Detail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author payno
 * @date 2020/3/25 09:53
 * @description
 */
@Mapper
public interface Check4Mapper3 {
    /**
     *  也可以使用@Inject+@Autowire来依赖注入
     *  也可以使用Mappers来获取（猜测是享元模式）
     */
    Check4Mapper3 MAPPER= Mappers.getMapper(Check4Mapper3.class);
    Check4DetailVo of(Check4 check4, Check4Detail detail);
    void updateVoDetail(@MappingTarget Check4DetailVo vo, Check4Detail detail);
}
