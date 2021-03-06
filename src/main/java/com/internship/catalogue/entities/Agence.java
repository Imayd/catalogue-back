package com.internship.catalogue.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@Data
@Table(name = "agences", uniqueConstraints = { @UniqueConstraint(columnNames = { "code" })})
public class Agence implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank
    @Size(min=6, max = 50)
	private String code;
	
	@NotBlank
	private String adrresse;
	
	@NotBlank
	private AgenceType type;
	
	@OneToOne
	@JoinColumn(name = "responsable_id", referencedColumnName = "id")
	private User responsable;
	

}
