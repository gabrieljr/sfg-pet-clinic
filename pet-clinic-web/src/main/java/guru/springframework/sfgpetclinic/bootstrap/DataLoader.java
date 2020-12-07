package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);


        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstname("Gabriel");
        owner1.setLastName("Junior");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("79999893254");

        Pet gabrielsPet1 = new Pet();
        gabrielsPet1.setPetType(savedDogPetType);
        gabrielsPet1.setOwner(owner1);
        gabrielsPet1.setBirthDate(LocalDate.now());
        gabrielsPet1.setName("Puffy");
        owner1.getPets().add(gabrielsPet1);

        Pet gabrielsPet2 = new Pet();
        gabrielsPet2.setPetType(savedDogPetType);
        gabrielsPet2.setOwner(owner1);
        gabrielsPet2.setBirthDate(LocalDate.now());
        gabrielsPet2.setName("Pluto");
        owner1.getPets().add(gabrielsPet2);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstname("Michael");
        owner2.setLastName("Jackson");
        owner2.setAddress("331 Brickerel");
        owner2.setCity("Boston");
        owner2.setTelephone("44999893254");

        Pet michaelsPet1 = new Pet();
        michaelsPet1.setPetType(savedDogPetType);
        michaelsPet1.setOwner(owner1);
        michaelsPet1.setBirthDate(LocalDate.now());
        michaelsPet1.setName("LittleDog");
        owner1.getPets().add(michaelsPet1);

        Pet michaelsPet2 = new Pet();
        michaelsPet2.setPetType(savedCatPetType);
        michaelsPet2.setOwner(owner1);
        michaelsPet2.setBirthDate(LocalDate.now());
        michaelsPet2.setName("Betty");
        owner1.getPets().add(michaelsPet2);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstname("Sam");
        vet1.setLastName("Winchester");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstname("Dean");
        vet2.setLastName("Winchester");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
