package fr.adaming.toulouse.SSN.service;

import java.util.List;

import fr.adaming.toulouse.SSN.model.Client;
import fr.adaming.toulouse.SSN.model.Commande;

public interface ICommandeService {
	
	
	public List<Commande> getAllCommandesService(Client cl);
    public Commande addCommande(Commande com,Client cl);
	public int deleteCommande(Commande com);

	public String genererCommandePDF(Commande com, double montant);
	

}
