package com.internship.catalogue.services;

import com.internship.catalogue.entities.Market;


public interface IMarketService {

	public Market addMarket(Market market) throws Exception;

	public Market updateMarket(Long id, Market market) throws Exception;
	
	public void deleteMarket(Long idMarket) throws Exception;


}
