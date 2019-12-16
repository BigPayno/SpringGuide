package com.payno.jpa;

import com.payno.jpa.querydsl.entity.QQueryDsl;
import com.payno.jpa.querydsl.entity.QueryDsl;
import com.payno.jpa.querydsl.repo.QueryDslRepo;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2019/11/28 14:41
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class QueryDslTests {
    @Autowired
    QueryDslRepo queryDslRepo;
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @Test
    public void insert() {
        IntStream.range(0, 25).forEach(num -> {
            QueryDsl queryDsl = QueryDsl.builder()
                    .name(String.format("%s%d", "payno", num)).age(num).build();
            queryDslRepo.save(queryDsl);
        });
    }

    /**
     * 利用Q类和JpaQueryFactory可以进行灵活的动态查询
     * 支持
     * 动态查询，多表联查
     * 范围查询。模糊查询
     * 返回结果拼装
     */
    @Test
    public void query() {
        QQueryDsl query = QQueryDsl.queryDsl;
        queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(query.id, query.name)
                .from(query)
                .where(query.updateTime.after(LocalDateTime.now().minusDays(1)))
                .fetchAll();
        QueryResults<Tuple> queryResults = jpaQuery.fetchResults();
        List<Tuple> tuples = queryResults.getResults();
        tuples.forEach(tuple -> {
            System.out.printf("id %s,name %s %n", tuple.get(query.id), tuple.get(query.name));
        });
    }

    /**
     * 时间范围查询
     */
    @Test
    public void predicate() {
        QQueryDsl queryDsl = QQueryDsl.queryDsl;
        BooleanExpression predicate = queryDsl.updateTime.dayOfYear().between(0, 365);
        queryDsl.updateTime.between(LocalDateTime.now().minusHours(3), LocalDateTime.now());
        queryDslRepo.findAll(predicate);
    }

    @Test
    public void reflect() {
        String className = QueryDsl.class.getName();

    }
}
