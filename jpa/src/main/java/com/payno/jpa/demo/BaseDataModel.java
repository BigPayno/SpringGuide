package com.payno.jpa.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.naming.OperationNotSupportedException;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2019/11/28 10:26
 * @description
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDataModel implements TimeFilter,NativeResponseAccessor,ToVo, ToExample {
    public static final String BLANK="";
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @Version
    private long version;
    private String nativeResponse;
    @Override
    public boolean timeFilter(Predicate<LocalDateTime> predicate) {
        return predicate.test(updateTime);
    }

    @Override
    public <T extends ToVo> BaseDataVo<T> toVo() throws OperationNotSupportedException{
        throw new OperationNotSupportedException(
                String.format("the class of %s doesn't support generating vo!",this.getClass())
        );
    }

    @Override
    public String[] ignoreProperties() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ToExample> Example<T> toExample() {
        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
                .withIgnoreNullValues();
        if(ignoreProperties()!=null&&ignoreProperties().length!=0){
            exampleMatcher=exampleMatcher.withIgnorePaths(ignoreProperties());
        }
        return Example.of((T)this,exampleMatcher);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ToExample> Example<T> toExample(ExampleMatcher matcher) {
        return Example.of((T)this,matcher);
    }
}
