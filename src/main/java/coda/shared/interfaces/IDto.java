package coda.shared.interfaces;

public interface IDto<Type, dtoType> {
    public Type fromDto(dtoType dto);
    public dtoType toDto();
}

