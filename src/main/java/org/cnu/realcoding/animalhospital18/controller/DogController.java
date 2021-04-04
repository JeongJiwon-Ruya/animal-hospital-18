package org.cnu.realcoding.animalhospital18.controller;
import org.cnu.realcoding.animalhospital18.domain.Dog;
import org.cnu.realcoding.animalhospital18.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DogController {
    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs") // Get시 GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs() {
        return dogManagementService.getAllDogs();
    }


    @PatchMapping("/dogs/kind")
    public void patchDogKind(@RequestParam String name, String kind){ dogManagementService.patchKind(name, kind);}

    @PatchMapping("/dogs/dog")
    public void patchDog(@RequestParam String name, @RequestBody Dog dog){ dogManagementService.patchDogInfo(name, dog);}

    @PatchMapping("/dogs/medical")
    public void addMedicalRecord(@RequestParam String name, String medical){dogManagementService.addMedicalRecord(name, medical);}
}
