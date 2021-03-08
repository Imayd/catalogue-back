package com.internship.catalogue.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "markets", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }),
		@UniqueConstraint(columnNames = { "abreviation" })})
public class Market  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=3, max = 30)
	private String nom;
	
	@NotBlank
	@Size(min=1, max = 5)
	private String abreviation;
	
	@NotBlank
	private Date dateAjout;
	@NotBlank
	private Date dateModification;
}
