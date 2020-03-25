package mapper;

import dto.Check4Dto;
import dto.Check4Vo;
import entity.Check4;
import org.mapstruct.Mapper;

/**
 * @author payno
 * @date 2020/3/25 09:10
 * @description
 */
@Mapper
public interface Check4Mapper {
    Check4Dto dtoFrom(Check4 check4);
    Check4Vo voFrom(Check4 check4);
    Check4 ofDto(Check4Dto dto);
    Check4 ofVo(Check4Vo vo);
}
