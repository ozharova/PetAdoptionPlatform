# Project Notes (Criteria Mapping)

## SOLID Refactor (Before/After)
Before:
- `PetRepository` mixed responsibilities: JDBC access + in-memory search/filter/sort.
- `PetService` depended on a concrete repository for data pool behavior.

After:
- `PetRepository` focuses only on JDBC CRUD.
- `PetDataPool` + `InMemoryPetDataPool` handle in-memory search/filter/sort.
- `PetService` depends on abstractions (`IPetRepository`, `PetDataPool`) rather than concrete classes.

Result:
- SRP: DB access and in-memory logic are separated.
- DIP (mandatory): `PetService` depends on interfaces, so repositories/data pools can be swapped without changing service code.

## Design Patterns
- Factory: `PetFactory.createPet(...)` centralizes object creation by type.
- Builder: `PetBuilder` allows fluent construction for optional fields.

Both are used in `Main` to build the initial demo flow and keep construction logic consistent.
