package fr.adaming.toulouse.SSN.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.adaming.toulouse.SSN.Dao.ILigneCommandeDao;
import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Produit;

@Service("lcService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeSerivice {

	// Association uml en java
	ILigneCommandeDao lcomDao;

	// setter pour l'injection de depndance
	public void setLcomDao(ILigneCommandeDao lcomDao) {
		this.lcomDao = lcomDao;
	}

	@Override
	public List<LigneCommande> getAllLigneCommandesService(Produit prod) {

		return lcomDao.getAllLigneCommandesDao(prod);
	}

	@Override
	public LigneCommande addLigneCommandeService(LigneCommande lcom, Produit prod) {
		lcom.setProd(prod);
		return lcomDao.addLigneCommande(lcom);
	}

	@Override
	public int deleteLigneCommandeService(LigneCommande lcom, Produit prod) {
	lcom.setProd(prod);
		return lcomDao.deleteLigneCommande(lcom);
	}

}
