package fr.adaming.toulouse.SSN.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.toulouse.SSN.Dao.ICategorieDao;
import fr.adaming.toulouse.SSN.Dao.IClientDao;
import fr.adaming.toulouse.SSN.model.Categorie;

@Service("catService")
@Transactional // pour rendre toutes le methodes de la classe transactionnelles
public class CategorieServiceImpl implements ICategorieService {
	// Association uml en java
	@Autowired
	ICategorieDao categorieDao;
	
	//Setter pour l'injection de dépendances	
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	

	@Override
	public List<Categorie> getAllCategoriesService() {

		return categorieDao.getAllCategoriesDao();
	}

	@Override
	public Categorie addCategorieService(Categorie cat) {

		return categorieDao.addCategorieDao(cat);
	}

	@Override
	public int updateCategorieService(Categorie cat) {

		return categorieDao.updateCategorieDao(cat);
	}

	@Override
	public int deleteCategorieService(Categorie cat) {

		return categorieDao.deleteCategorieDao(cat);
	}

	@Override
	public Categorie getCategorieByIdService(Categorie cat) {
		
		return categorieDao.getCategorieById(cat);
	}

	

	
	
	
	
}
