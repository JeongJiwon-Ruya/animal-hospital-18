package org.cnu.realcoding.animalhospital18.repository;

import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.exception.DogAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    public void insertDog(Dog dog) {
        if(existsByDog(dog)){
            throw new DogAlreadyExistsException();
        }
        mongoTemplate.insert(dog);
    }
    public boolean existsByDog(Dog dog) { //중복되는 Dog 체크
        return mongoTemplate.exists(
                Query.query(new Criteria().andOperator(
                        Criteria.where("name").is(dog.getName()),
                        Criteria.where("ownerName").is(dog.getOwnerName()),
                        Criteria.where("ownerPhoneNumber").is(dog.getOwnerPhoneNumber())
                        )
                ),
                Dog.class
        );
    }

    public List<Dog> findAllDog() {
        return mongoTemplate.findAll(Dog.class);
    }
    // 모든 Dog 반환 함수
}
