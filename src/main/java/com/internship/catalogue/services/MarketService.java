package com.internship.catalogue.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.catalogue.dto.MarketDTO;
import com.internship.catalogue.entities.Market;
import com.internship.catalogue.repositories.MarketRepository;

@Service
public class MarketService implements IMarketService {

	@Autowired
	private MarketRepository marketRepository;
	
	
	@Override
	public MarketDTO addMarket(Market market) throws Exception {
		
		//boolean checkNom = true;
		//boolean checkAbrv = true;
		
		if(!marketRepository.findByNom(market.getNom()).isEmpty()) {
			
			throw new Exception("Nom  " + market.getNom()+ " exist deja");	
		}
		else if(!marketRepository.findByAbreviation(market.getAbreviation()).isEmpty()) {
			
			throw new Exception("L'abreviation  " + market.getAbreviation()+ " exist deja");
			
		}else
		{
			Market mr = this.marketRepository.save(market);
			
			MarketDTO m = new MarketDTO();
			m.setNom(mr.getNom());
			m.setAbreviation(mr.getAbreviation());
			m.setDateAjout(mr.getDateAjout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			m.setDateModification(mr.getDateModification().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			return m;

			/*
			 * Locale locale = new Locale("fr", "FR"); DateFormat dateFormat =
			 * DateFormat.getDateInstance(DateFormat.DEFAULT, locale); String date =
			 * dateFormat.format(new Date());
			 */
			
			//m.setDateAjout(new Date());
			//m.setDateAjout(Calendar.getInstance().getTime());
			//m.setDateAjout(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

			//m.setDateModification(new Date());
			//m.setDateAjout(dateAjout);
			//m = this.marketRepository.save(market);
			//return this.marketRepository.save(market);
		}
	}

	@Override
	public MarketDTO updateMarket(Long id, Market market) throws Exception {
		if (id == null)
            throw new Exception("Id est null");
		
		if(!marketRepository.findByNom(market.getNom()).isEmpty()) {
			
			throw new Exception("Nom  " + market.getNom()+ " exist deja");	
		}
		else if(!marketRepository.findByAbreviation(market.getAbreviation()).isEmpty()) {
			
			throw new Exception("Abreviation  " + market.getAbreviation()+ " exist deja");
			
		}else
		{
			Market m = marketRepository.findById(id).get();
			
			MarketDTO mdto = new MarketDTO();
			
			
			mdto.setNom(market.getNom());
			mdto.setAbreviation(market.getAbreviation());
			mdto.setDateAjout(m.getDateAjout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			market.setId(id);
			market.setDateAjout(m.getDateAjout());
			Market nv = this.marketRepository.save(market);
			mdto.setDateModification(nv.getDateModification().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			//Market m = new Market();
			//m.setDateModification(new Date());
	        //m = this.marketRepository.save(market);
	        //return this.marketRepository.save(market);
			return mdto;
		}
	}

	@Override
	public void deleteMarket(Long idMarket) throws Exception {

		marketRepository.deleteById(idMarket);
	}

	@Override
	public List<MarketDTO> getAllMarkets() throws Exception {
		
		List<Market> listMarkets = marketRepository.findAll();
		List<MarketDTO> listMarketsDTO = new ArrayList<>();
		listMarkets.forEach(market -> {
			MarketDTO marketDTO = new MarketDTO();
			marketDTO.setNom(market.getNom());
			marketDTO.setAbreviation(market.getAbreviation());
			marketDTO.setDateAjout(market.getDateAjout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			marketDTO.setDateModification(market.getDateModification().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
			listMarketsDTO.add(marketDTO);
			
		});
		
		return listMarketsDTO;
	}

	@Override
	public MarketDTO getMarket(Long id) throws Exception {
		
		/*
		 * Market m = marketRepository.findById(id).get(); Market market = new Market();
		 * m.getDateAjout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		 * 
		 * return marketRepository.findById(id).get();
		 */
		
		Market m = marketRepository.findById(id).get();
		
		MarketDTO mdto = new MarketDTO();
		mdto.setNom(m.getNom());
		mdto.setAbreviation(m.getAbreviation());
		
		mdto.setDateAjout(m.getDateAjout().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
		mdto.setDateModification(m.getDateModification().format(DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm")));
		return mdto;
		
		//return marketRepository.getOne(id)
	}
	

}
