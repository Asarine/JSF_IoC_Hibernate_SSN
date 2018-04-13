package fr.adaming.toulouse.SSN.service;

import java.util.List;

import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Produit;

public interface ILigneCommandeSerivice {
	
	public List<LigneCommande> getAllLigneCommandesService();
    public LigneCommande addLigneCommandeService(LigneCommande lcom);
    public int deleteLigneCommandeService(LigneCommande lcom);
	
	
    

}
