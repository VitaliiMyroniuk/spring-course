package beans.converters;

public interface DtoConverter<E, D> {

    E convertToEntity(D dto);

    D convertToDto(E entity);
}
