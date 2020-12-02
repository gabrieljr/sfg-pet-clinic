package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

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
        michaelsPet1.setName("Doguinho");
        owner1.getPets().add(michaelsPet1);

        Pet michaelsPet2 = new Pet();
        michaelsPet2.setPetType(savedCatPetType);
        michaelsPet2.setOwner(owner1);
        michaelsPet2.setBirthDate(LocalDate.now());
        michaelsPet2.setName("Bety");
        owner1.getPets().add(michaelsPet2);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstname("Sam");
        vet1.setLastName("Winchester");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstname("Dean");
        vet2.setLastName("Winchester");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
