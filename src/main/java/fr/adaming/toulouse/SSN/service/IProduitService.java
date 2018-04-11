package fr.adaming.toulouse.SSN.service;

import java.util.List;

import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.model.Produit;

public interface IProduitService {
	
	public List<Produit> getAllProduitsService(Categorie cat);

	public Produit ajouterProduitService(Produit prod, Categorie cat);

	public int deleteProduitService(Produit prod);

	public Produit rechercherProduitService(Produit prod);

	public int updateProduitService(Produit prod,Categorie cat);

	public List<Produit> getAllProduitsService();

	public List<Produit> getProduitsRechService(String motCle);
	

}
