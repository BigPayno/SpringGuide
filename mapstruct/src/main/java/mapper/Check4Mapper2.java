package mapper;

import dto.Check4Dto;
import entity.Check4;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author payno
 * @date 2020/3/25 09:22
 * @description
 */
@Mapper
public interface Check4Mapper2 {
    @Mapping(target = "userName",source = "cusName")
    Check4Dto dtoFrom(Check4 check4);
}
