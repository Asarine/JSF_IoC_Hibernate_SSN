package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import fr.adaming.toulouse.SSN.model.Client;
import fr.adaming.toulouse.SSN.model.Commande;

public interface ICommandeDao {
	
	public List<Commande> getAllCommandesDao(Client cl);
	public Commande addCommande(Commande com);
	public int deleteCommande(Commande com);
	
	
	

}
