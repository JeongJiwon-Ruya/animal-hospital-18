package org.cnu.realcoding.animalhospital18.service;

import lombok.Getter;
import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {
    @Getter
    @Autowired

    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }
}
