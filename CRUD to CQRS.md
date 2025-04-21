# Example 1
We'll start by transforming the update Pet feature to a CQRS pattern, because the input requires fewer data than the one sent.
Here's the initial implementation:
- Looking at the PetRestController, we can see that the updatePet method takes a PetDto as a parameter but uses only the pet name, birthday and type to update the pet.: not the visits
- We can create a new command class called UpdatePetCommand that contains only those fields, and use it in the updatePet method instead of PetDto
- We can create a new class to handle it: UpdatePetCommandHandler. It takes an UpdatePetCommand as a parameter and uses it to update the pet as the controller does.
- We can modify the PetRestController to use the UpdatePetCommandHandler instead of the updatePet method.

# Changes
- changing openapi.yml to make "updatePet" take an UpdatePetCommandDto instead of a PetDto (don't forget to change PetsApi accordingly if you don't generate sources automatically)
- Defining UpdatePetCommandDto class with only id, name, birthDate and type fields (copy / paste from PetDto to avoid misses on field validation + useless code removal : extra fields (visits, ownerId), equals(..) + hashcode())

Please note that the UpdateCommandDto.id field is now mandatory.

# Example 2
Next one is the "Add Pet" feature.
Looking at the Front-End, the pet-add.component.html doesn't have any reference to the pet visits, meaning that end users can't create a Pet and creates a visit for him at the same time.
Whereas the Back-End PetRestController does. Here we have the phenomenon of the automatic exposure of too many attributes to the extra world but outer people don't use them. It certainly have created an extra work during implementation (due to the Visit mapping and persistence) and will do again if we amend the domain entities.

# Changes
I had some doubt about the test implementation in `PetRestControllerTests.testAddPetSuccess()` because, when dealing with insertion some rules apply differently than when updating entities, and particularly the test doesn't mock clinicService.savePet(). Hopefully the test class is close to an e2e test and in can be converted to such one with few modifications:
- replace `@MockedBean` by `@Autowired` on the `clinicService` field
- remove all `given(..)` applied to `clinicService`
- add `@ActiveProfiles({"jdbc", "hsqldb"})` on top of the class
- add `@Transactional` on top of the class to make test methods rollback their database modifications and avoid test dependencies
- let's disable `testGetAllPetsNotFound()` because we have data now.

=> Good news ! all tests pass ... except `PetRestControllerTests.testAddPetSuccess()` which fails with a 404 error. 

Digging (debugging) into the problem shows a database constraint violation because the entry (the new Pet) lacks an ownerId, it also shows that it go through an update instead of an insert, because of the presence of an id on the new Pet. Those 2 elements are pretty interesting because it highlights some mistakes in the contract of the API :
- the ownerId is optional
- the id is allowed on a new instance
  Let's fix the test by having a dedicated Pet instance and make the test pass, then we'll be back to our Command conversion.

After this preamble, let's go further and apply the same methodology as in example 1
- let's create a `AddPetCommandDto` based on `PetDto`
    - we know that it requires an ownerId, therefore we adapt the annotations to make it mandatory and pass its type to int
    - we know that it doesn't require an id therefore we remove it
    - from the test, we see that visits are not taken into account by the service and not persisted so we can remove them too
- make this DTO used by the Controller: change the argument from `PetDto` to `AddPetCommandDto`.
- make the Controller use a new command handler named `AddPetCommandHandler`. The previous algorithm was based on the monolithic Mapper to create a Pet entity from the DTO, but since we know that some fields are not necessary or even absent the mapping will be lighter and we make our own directly in the handler.

The test still passes.
=> the contract is lighter and more precise.
