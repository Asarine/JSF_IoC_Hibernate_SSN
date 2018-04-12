package fr.adaming.toulouse.SSN.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.toulouse.SSN.Dao.ICategorieDao;
import fr.adaming.toulouse.SSN.Dao.IProduitDao;
import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.model.Produit;


@Service("prodService")
@Transactional
public class ProduitServiceImpl implements IProduitService{
    
	//Association uml en java 
	@Autowired
	IProduitDao prodDao;
	
	
	@Autowired
	ICategorieDao catDao; 
	
	//setter pour l'injection de dependance
	public void setProdDao(IProduitDao prodDao) {
		this.prodDao = prodDao;
	}
	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	
	
	
	
	
	
	
	@Override
	public List<Produit> getAllProduitsService(Categorie cat) {
		return prodDao.getAllProduitsDao(cat);
	}

	@Override
	public Produit ajouterProduitService(Produit prod, Categorie cat) {
		
		Categorie catOut = catDao.getCategorieById(cat);
		prod.setCat(catOut);
		return prodDao.ajouterProduitDao(prod);
	}

	@Override
	public int deleteProduitService(Produit prod) {
		
		return prodDao.deleteProduitDao(prod);
	}

	@Override
	public Produit rechercherProduitService(Produit prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProduitService(Produit prod, Categorie cat) {
		System.out.println(cat.getIdCategorie());
		Categorie catOUT = catDao.getCategorieById(cat);
		prod.setCat(catOUT);
		return prodDao.updateProduitDao(prod);
	}

	@Override
	public List<Produit> getAllProduitsService() {
		
		return prodDao.getAllProduitsDao();
	}

	@Override
	public List<Produit> getProduitsRechService(String motCle) {
		// TODO Auto-generated method stub
		return null;
	}

}
