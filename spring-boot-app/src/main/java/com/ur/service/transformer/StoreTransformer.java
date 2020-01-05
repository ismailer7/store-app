package com.ur.service.transformer;

import org.springframework.stereotype.Component;

import com.ur.domain.Store;
import com.ur.pojo.StoreDTO;

@Component
public class StoreTransformer extends Transformer<Store, StoreDTO>{

	@Override
	public StoreDTO toDTO(Store e) {
		StoreDTO dtoStore = new StoreDTO(
				e.getId(),
				e.getName(),
				e.getTypes(),
				e.getIcon(),
				e.isOpen(),
				e.getRating(),
				e.getVicinity()
		);
		return dtoStore;
	}

	@Override
	public Store toEntity(StoreDTO d) {
		Store store = new Store(
				d.getId(),
				d.getName(),
				d.getVicinity(),
				d.getTypes(),
				d.getIcon(),
				d.isOpen(),
				d.getRate()
		);
		return store;
	}

	
}
