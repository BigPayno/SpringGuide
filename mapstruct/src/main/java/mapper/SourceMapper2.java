package mapper;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.Source;
import entity.SubSource;
import entity.SubTarget;
import entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author payno
 * @date 2020/3/25 09:38
 * @description
 */
@Mapper
public interface SourceMapper2 {
    public static SourceMapper2 INSTANCE = Mappers.getMapper(SourceMapper2.class);

    @Mapping(source = "deleted",target = "result")
    SubTarget from(SubSource subSource);

    @Mapping(source = "subSource",target = "subTarget")
    Target from(Source source);

    default Boolean from(Integer num){
        if(num!=null){
            return (num.equals(0))?Boolean.FALSE:Boolean.TRUE;
        }else {
            return null;
        }
    }
}
