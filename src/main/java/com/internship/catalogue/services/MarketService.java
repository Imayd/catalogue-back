package com.internship.catalogue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.catalogue.entities.Market;
import com.internship.catalogue.repositories.MarketRepository;

@Service
public class MarketService implements IMarketService {

	@Autowired
	private MarketRepository marketRepository;
	
	
	@Override
	public Market addMarket(Market market) throws Exception {
		
		//boolean checkNom = true;
		//boolean checkAbrv = true;
		
		if(!marketRepository.findByNom(market.getNom()).isEmpty()) {
			
			throw new Exception("Nom  " + market.getNom()+ " exist deja");	
		}
		else if(!marketRepository.findByAbreviation(market.getAbreviation()).isEmpty()) {
			
			throw new Exception("Abreviation  " + market.getAbreviation()+ " exist deja");
			
		}else
		{
			Market m = this.marketRepository.save(market);
			return m;
		}
	}

	@Override
	public Market updateMarket(Long id, Market market) throws Exception {
		if (id == null)
            throw new Exception("Id est null");
		
		if(!marketRepository.findByNom(market.getNom()).isEmpty()) {
			
			throw new Exception("Nom  " + market.getNom()+ " exist deja");	
		}
		else if(!marketRepository.findByAbreviation(market.getAbreviation()).isEmpty()) {
			
			throw new Exception("Abreviation  " + market.getAbreviation()+ " exist deja");
			
		}else
		{
			market.setId(id);
	        Market m = this.marketRepository.save(market);
	        return m;
		}
	}

	@Override
	public void deleteMarket(Long idMarket) throws Exception {

		marketRepository.deleteById(idMarket);
	}

}
