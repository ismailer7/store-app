package com.ur.service.transformer;

import java.util.List;
import java.util.stream.Collectors;


public abstract class Transformer<E, D> {

	public abstract D toDTO(E e);
	
	public abstract E toEntity(D d);
	
	public List<D> toDTOList(List<E> entityList) {
		List<D> ds = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
		return ds;
	}
	
	public List<E> toEntityList(List<D> dtoList) {
		return dtoList.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}
}
