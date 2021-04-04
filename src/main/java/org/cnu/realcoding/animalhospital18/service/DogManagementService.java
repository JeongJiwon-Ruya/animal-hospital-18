package org.cnu.realcoding.animalhospital18.service;

import lombok.Getter;
import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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


    public void patchKind(String name, String kind){ dogRepository.modifyDogKind(name, kind);}

    public void patchDogInfo(String name, Dog dog){ dogRepository.modifyDog(name, dog);}

    public void addMedicalRecord(String name, String medical){}
}
