package fr.adaming.toulouse.SSN.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.toulouse.SSN.Dao.ILigneCommandeDao;
import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Produit;

@Service("lcomService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeSerivice {

	// Association uml en java
	@Autowired
	ILigneCommandeDao lcomDao;

	// setter pour l'injection de depndance
	public void setLcomDao(ILigneCommandeDao lcomDao) {
		this.lcomDao = lcomDao;
	}

	@Override
	public List<LigneCommande> getAllLigneCommandesService() {

		return lcomDao.getAllLigneCommandesDao();
	}

	@Override
	public LigneCommande addLigneCommandeService(LigneCommande lcom) {
		
		return lcomDao.addLigneCommande(lcom);
	}

	@Override
	public int deleteLigneCommandeService(LigneCommande lcom) {
	System.out.println("==============================================="+lcom.getIdLComm());
		return lcomDao.deleteLigneCommande(lcom);
	}

}
