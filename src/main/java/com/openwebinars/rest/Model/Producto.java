package com.openwebinars.rest.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private float precio;

	@Column(name = "imagen")
	private String imagen;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	// @JsonBackReference
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// @ManyToMany(mappedBy="productos", fetch = FetchType.EAGER)
	@ManyToMany(mappedBy = "productos")
	@Builder.Default
	private Set<Lote> lotes = new HashSet<>();

}
