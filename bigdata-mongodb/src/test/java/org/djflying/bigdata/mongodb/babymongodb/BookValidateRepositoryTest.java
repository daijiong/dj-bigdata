package babymongodb;

import org.djflying.bigdata.mongodb.babymonogodb.config.MongoConfig;
import org.djflying.bigdata.mongodb.babymonogodb.model.BookValidatePerformance;
import org.djflying.bigdata.mongodb.babymonogodb.repository.BookValidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class BookValidateRepositoryTest {


    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BookValidateRepository bookValidateRepository;

    @Test
    public void testBookValidateRepository(){
        System.out.println(bookValidateRepository.count());
    }



    @Test
    public void insertBookValidateRepository(){
        BookValidatePerformance bookValidatePerformance = new BookValidatePerformance();
        bookValidatePerformance.setUnitKey("a");
        bookValidateRepository.save(bookValidatePerformance);
    }
}
