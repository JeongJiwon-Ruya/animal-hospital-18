package org.cnu.realcoding.animalhospital18.repository;

import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.exception.DogAlreadyExistsException;
import org.cnu.realcoding.animalhospital18.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Update.update;

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


    public Dog getDogByName(String dogName) {
        Query q = new Query(Criteria.where("name").is(dogName));
        if(!mongoTemplate.exists(q, Dog.class))
            throw new DogNotFoundException();

        return mongoTemplate.findOne(q, Dog.class);
    }

    public Dog getDogByOwnerName(String ownerName) {
        Query q = new Query(Criteria.where("ownerName").is(ownerName));
        if(!mongoTemplate.exists(q, Dog.class))
            throw new DogNotFoundException();

        return mongoTemplate.findOne(q, Dog.class);
    }

    public Dog getDogByPhoneNumber(String phoneNumber) {
        Query q = new Query(Criteria.where("ownerPhoneNumber").is(phoneNumber));
        if(!mongoTemplate.exists(q, Dog.class))
            throw new DogNotFoundException();
        return mongoTemplate.findOne(q, Dog.class);
    }

    public Dog getDogByNameAndOwnerInfo(String name, String ownerName, String phoneNumber) {
        Query q = Query.query(new Criteria().andOperator(Criteria.where("name").is(name),
                Criteria.where("ownerName").is(ownerName),
                Criteria.where("ownerPhoneNumber").is(phoneNumber)));

        if(!mongoTemplate.exists(q, Dog.class))
            throw new DogNotFoundException();

        return mongoTemplate.findOne(q, Dog.class);


    // 모든 Dog 반환 함수

    public void modifyDog(String name, Dog dog) {
        if(!existsByDogName(name)){
            throw new DogNotFoundException();
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("name", dog.getName()).set("kind", dog.getKind())
                .set("ownerName", dog.getOwnerName()).set("ownerPhoneNumber", dog.getOwnerPhoneNumber());

        mongoTemplate.upsert(query, update, Dog.class);
    }

    public void modifyDogKind(String name, String kind){
        if(!existsByDogName(name)){
            throw new DogNotFoundException();
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("kind", kind);
        mongoTemplate.upsert(query, update, Dog.class);
    }

    public boolean existsByDogName(String name){
        return mongoTemplate.exists(
                Query.query(new Criteria().where("name").is(name)), Dog.class
        );

    }
}
