package com.internship.catalogue.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internship.catalogue.entities.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

	  List<Market> findByNom(String nom);
	  List<Market> findByAbreviation(String abreviation);


}
