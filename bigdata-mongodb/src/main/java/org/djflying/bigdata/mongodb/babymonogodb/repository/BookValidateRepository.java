package org.djflying.bigdata.mongodb.babymonogodb.repository;

import org.djflying.bigdata.mongodb.babymonogodb.model.BookValidatePerformance;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 */
public interface BookValidateRepository extends MongoRepository<BookValidatePerformance, Integer> {
}
