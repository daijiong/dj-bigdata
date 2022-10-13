package org.djflying.bigdata.mongodb.babymongodb;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.djflying.bigdata.mongodb.babymonogodb.model.BookValidatePerformance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;

/**
 * 测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/beans.xml")
public class BookValidateTest {

    @Resource
    private MongoOperations mongoOperations;

    @Test
    public void count() {
        System.out.println("集合的count" + mongoOperations.count(null, "bookvalidate"));
    }

    @Test
    public void mockData() {
        for (int i = 0; i < 10000; i++) {
            try {
                BookValidatePerformance performance = new BookValidatePerformance();
                performance.setEnv(new Random().nextInt(2) == 0 ? "prod" : "stage");
                performance.setCallTime(new Date().toString());
                performance.setId(i);
                performance.setInvalidCabinFlag(new Random().nextInt(2) + "");
                performance.setPriceChangeFlag(new Random().nextInt(2) + "");
                performance.setSpendTime(new Random().nextInt(1000));
                performance.setSupplierId("124");
                performance.setTraceId("WX" + new Random().nextInt(100000));
                performance.setUnitKey("0124_OW_SHA_HKG_20171007_null_1_0_Y#FM" + i + "_20171007_S#GDS-WS#CTRIP#1");
                mongoOperations.insert(performance, "bookvalidate2");
            } catch (Exception e) {
                System.out.print(e.toString());
            }

        }
    }

    @Test
    public void find() {

        // 查询条件 （ env = prod 或 env = stage ）并且 traceId = WX72131
        mongoOperations.find(new Query(Criteria.where("env").is("prod").orOperator(Criteria.where("env").is("stage")).where("traceId").is("WX72131")),
            BookValidatePerformance.class, "bookvalidate").stream().forEach(performance -> {
                System.out.println(performance.getTraceId());
            });

        // 查询env = prod  并且 env = stage
        //        mongoOperations.find(new Query(Criteria.where("env").is("prod").andOperator(Criteria.where("env").is("stage"))), BookValidatePerformance.class, "bookvalidate").stream().forEach(performance -> {
        //            System.out.println(performance.getTraceId());
        //        });

        //        //// 查询耗时 > 100 并且环境标志=prod
        //        mongoOperations.find(new Query(Criteria.where("spendTime").gt(100).where("env").is("prod")), BookValidatePerformance.class, "bookvalidate").stream().forEach(performance -> {
        //            System.out.println(performance.getTraceId());
        //        });
        //
        //        //// 查询耗时 > 100 供应商ID = 124
        //        mongoOperations.find(new Query(Criteria.where("spendTime").gt(100).where("supplierId").is("124")), BookValidatePerformance.class, "bookvalidate").stream()
        //            .forEach(performance -> {
        //                System.out.println(performance.getTraceId());
        //            });
    }

    @Test
    public void delete() {
        // 移除集合
        mongoOperations.dropCollection("bookvalidate");
        // 移除表中所有的环境prod的数据
        mongoOperations.remove(new Query(Criteria.where("env").is("prod")), "bookvalidate");

        // 移除表中所有的数据
        mongoOperations.remove(null, "bookvalidate");
    }

    @Test
    public void update() {
        // 更新所有符合条件的数据
        mongoOperations.updateMulti(new Query(Criteria.where("env").is("stage")), new Update().set("spendTime", 99999), "bookvalidate");

        // 更新所有符合条件的行+1 只更新1条
        mongoOperations.updateFirst(new Query(Criteria.where("env").is("stage")), new Update().inc("spendTime", 1), "bookvalidate");
    }

    @Test
    public void aggregate() {

        //// 去重复后统计携程验价 成功量，变价量，虚舱量  查不出来怎么办？
        //        TypedAggregation<BookValidatePerformance> aggregation = Aggregation.newAggregation(BookValidatePerformance.class,
        //                Aggregation.group("traceId").count().as("allcount"),
        //            Aggregation.match(Criteria.where("env").is("prod")));
        //
        //        AggregationResults<BasicDBObject> object = mongoOperations.aggregate(aggregation, "bookvalidate1", BasicDBObject.class);

        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //--
        //// 统计携程验价 成功量，变价量，虚舱量
        TypedAggregation<BookValidatePerformance> aggregation = Aggregation.newAggregation(BookValidatePerformance.class, Aggregation.match(Criteria.where("env").is("prod")),
            Aggregation.group("traceId").count().as("allcount").count().as("priceChangeFlag").count().as("invalidCabinFlag"));

        AggregationResults<BasicDBObject> object = mongoOperations.aggregate(aggregation, "bookvalidate1", BasicDBObject.class);
    }

    @Test
    public void mapReduce() {
    }

}
