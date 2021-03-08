package com.internship.catalogue.services;

import java.util.List;

import com.internship.catalogue.dto.MarketDTO;
import com.internship.catalogue.entities.Market;


public interface IMarketService {

	public MarketDTO addMarket(Market market) throws Exception;

	public MarketDTO updateMarket(Long id, Market market) throws Exception;
	
	public void deleteMarket(Long idMarket) throws Exception;

	public List<MarketDTO> getAllMarkets() throws Exception;
	
	public MarketDTO getMarket(Long id) throws Exception;



}
