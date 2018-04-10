package fr.adaming.toulouse.SSN.service;

import java.util.List;

import fr.adaming.toulouse.SSN.model.Categorie;

public interface ICategorieService {
	public List<Categorie> getAllCategoriesService();

	public Categorie addCategorieService(Categorie cat);

	public int updateCategorieService(Categorie cat);

	public int deleteCategorieService(Categorie cat);

	public Categorie getCategorieByIdService(Categorie cat);
	
	
	
	
}
