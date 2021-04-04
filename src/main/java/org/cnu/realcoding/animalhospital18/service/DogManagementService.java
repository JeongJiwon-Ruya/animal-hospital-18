package org.cnu.realcoding.animalhospital18.service;

import lombok.Getter;
import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }

    public Dog getDogByName(String dogName){
        return dogRepository.getDogByName(dogName);
    }

    public Dog getDogByOwnerName(String ownerName) {
        return dogRepository.getDogByOwnerName(ownerName);
    }

    public Dog getDogByPhoneNumber(String phoneNumber) {
        return dogRepository.getDogByPhoneNumber(phoneNumber);
    }

    public Dog getDogByNameAndOwnerInfo(String name, String ownerName, String phoneNumber) {
        return dogRepository.getDogByNameAndOwnerInfo(name, ownerName, phoneNumber);
    }
}
