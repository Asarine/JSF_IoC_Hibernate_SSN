package fr.adaming.toulouse.SSN.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.toulouse.SSN.Dao.ICommandeDao;
import fr.adaming.toulouse.SSN.model.Client;
import fr.adaming.toulouse.SSN.model.Commande;


@Service("comService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {
   //Association uml en java
	@Autowired
	ICommandeDao comDao;
	
	@Override
	public List<Commande> getAllCommandesService(Client cl) {
		return comDao.getAllCommandesDao(cl);
	}

	@Override
	public Commande addCommande(Commande com, Client cl) {
		com.setCl(cl);
		return comDao.addCommande(com);
	}

	@Override
	public int deleteCommande(Commande com) {
		return comDao.deleteCommande(com);
	}

	@Override
	public String genererCommandePDF(Commande com, double montant) {
		// TODO Auto-generated method stub
		return null;
	}

}
