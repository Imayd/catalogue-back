package com.internship.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDTO {

	private String nom;
	private String abreviation;
	private String dateAjout;
	private String dateModification;
}
