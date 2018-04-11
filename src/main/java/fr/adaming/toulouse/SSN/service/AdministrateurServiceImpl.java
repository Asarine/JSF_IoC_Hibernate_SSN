package fr.adaming.toulouse.SSN.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.toulouse.SSN.Dao.IAdministateurDao;
import fr.adaming.toulouse.SSN.model.Administrateur;

public class AdministrateurServiceImpl implements IAdministrateurService {

	@Autowired
	private IAdministateurDao adDao;

	@Override
	public Administrateur isExist(Administrateur ad) {
		return adDao.isExist(ad);
	}

}
