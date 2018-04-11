package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.model.Produit;

public interface IProduitDao {
	
	
	public List<Produit> getAllProduitsDao(Categorie cat);

	public Produit ajouterProduitDao(Produit prod);

	public int updateProduitDao(Produit prod);

	public int deleteProduitDao(Produit prod);

	public Produit rechercherProduitDao(Produit prod);

	public List<Produit> getAllProduitsDao();
	
	
	

}
