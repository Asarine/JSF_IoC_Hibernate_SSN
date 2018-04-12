package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Produit;

public interface ILigneCommandeDao {
	
	public List<LigneCommande> getAllLigneCommandesDao(Produit prod);
	public LigneCommande addLigneCommande(LigneCommande lcom);
	public int deleteLigneCommande(LigneCommande lcom);
	

}
