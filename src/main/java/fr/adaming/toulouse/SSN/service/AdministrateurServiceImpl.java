package fr.adaming.toulouse.SSN.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.toulouse.SSN.Dao.IAdministateurDao;
import fr.adaming.toulouse.SSN.model.Administrateur;

@Service("adService")
@Transactional
public class AdministrateurServiceImpl implements IAdministrateurService {

	@Autowired
	private IAdministateurDao adDao;
	
	//Setter pour l'injection de dépendances
	public void setAdDao(IAdministateurDao adDao) {
		this.adDao = adDao;
	}


	@Override
	public Administrateur isExist(Administrateur ad) {
		return adDao.isExist(ad);
	}


}
