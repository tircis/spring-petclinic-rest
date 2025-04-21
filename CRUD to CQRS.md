
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
