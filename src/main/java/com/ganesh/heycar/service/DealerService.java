package com.ganesh.heycar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.heycar.db.DealerRepository;
import com.ganesh.heycar.model.Dealer;
import com.ganesh.heycar.model.DealerEntity;
import com.ganesh.heycar.util.HeyCarUtils;

@Service
public class DealerService {

	@Autowired
	DealerRepository dealerRepository;
	
	
	public void updateDealers(List<Dealer> dealers) {
		
		 List<DealerEntity> list=dealers.stream().map(dealer->new DealerEntity(dealer.getID(), dealer.getName())).collect(Collectors.toList());
		dealerRepository.saveAll(list);
	}


	public Dealer findDealerByID(long iD) {
		// TODO Auto-generated method stub
		Dealer dealer=null;
		Optional<DealerEntity> data=dealerRepository.findById(iD);
		if(data.isPresent()) {
			dealer=HeyCarUtils.getDealer(data.get());
		}
		return dealer;
	}
	
	
	public DealerEntity findDealerEntityByID(long id) {

		return dealerRepository.findById(id).get();
	}


	public List<Dealer> findAllDealers() {
		// TODO Auto-generated method stub
		 List<DealerEntity> list=dealerRepository.findAll();
		 return list.stream().map(entity->HeyCarUtils.getDealer(entity)).collect(Collectors.toList());
	}

}
