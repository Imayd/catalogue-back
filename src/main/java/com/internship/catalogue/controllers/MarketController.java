package com.internship.catalogue.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.catalogue.dto.MarketDTO;
import com.internship.catalogue.entities.Market;
import com.internship.catalogue.services.MarketService;

@RestController
@RequestMapping(path = "/api/markets/")
public class MarketController {

	@Autowired
	private MarketService marketService;
	 
	@PostMapping
	@PreAuthorize("hasRole('ROLE_RESPONSABLE_AGENCE') or hasRole('ROLE_ADMIN')")
	public MarketDTO addMarket(@Valid @RequestBody Market market) throws Exception {
		
		MarketDTO m = this.marketService.addMarket(market);
	     return m;
	}
	
	@PutMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_RESPONSABLE_AGENCE') or hasRole('ROLE_ADMIN')")
    public MarketDTO updateMarket(@PathVariable("id") Long id, @RequestBody Market market) throws Exception {
		MarketDTO m = this.marketService.updateMarket(id, market);
        return m;
    }
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasRole('ROLE_RESPONSABLE_AGENCE') or hasRole('ROLE_ADMIN')")
    public void deleteMarket(@PathVariable("id") Long param) throws Exception {
	        this.marketService.deleteMarket(param);
	 }
	
	 @GetMapping(value = "/")
	 @PreAuthorize("hasRole('ROLE_RESPONSABLE_AGENCE') or hasRole('ROLE_ADMIN')")
	    public List<MarketDTO> getAllMarkets() throws Exception {
	        List<MarketDTO> markets = this.marketService.getAllMarkets();
	        return markets;
	 }
	 

	 @GetMapping(path = "{id}")
	 @PreAuthorize("hasRole('ROLE_RESPONSABLE_AGENCE') or hasRole('ROLE_ADMIN')")
	 public MarketDTO getMarket(@PathVariable("id") Long id) throws Exception {
		 MarketDTO m = this.marketService.getMarket(id);
		return m;
	 }
}
