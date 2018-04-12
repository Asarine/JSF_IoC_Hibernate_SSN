package fr.adaming.toulouse.SSN.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;

import fr.adaming.toulouse.SSN.model.Administrateur;
import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.model.Produit;
//import fr.adaming.toulouse.SSN.service.IProduitService;
import fr.adaming.toulouse.SSN.service.IAdministrateurService;
import fr.adaming.toulouse.SSN.service.ICategorieService;
import fr.adaming.toulouse.SSN.service.IProduitService;

@ManagedBean(name = "adMB")
@RequestScoped
public class AdministrateurManagedBean {

	// Attributs
	@Autowired
	private IAdministrateurService adminService;
	@Autowired
	private ICategorieService categService;

	private Administrateur administrateur;
	private List<Categorie> listecateg;
	private List<Produit> listeProduits;

	// TODO quand Produit et pret
	private IProduitService prService;

	private Produit pr;

	public AdministrateurManagedBean() {
		this.administrateur = new Administrateur();
	}

	public String seConnecter() {

		try {

			Administrateur adOut = adminService.isExist(this.administrateur);
			// this.listeProduits = prService.getAllProduits(pr);
			this.listecateg = categService.getAllCategoriesService();

			// Ajouter l'administrateur comme attribut de la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieList", listecateg);

			return "accueilAdmin";

		} catch (Exception ex) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'identifiant ou le mot de passe est incorrecte"));

		}

		return "loginAdmin";

	}

	public IAdministrateurService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}

	public IProduitService getPrService() {
		return prService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	public ICategorieService getCategService() {
		return categService;
	}

	public void setCategService(ICategorieService categService) {
		this.categService = categService;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public List<Categorie> getListecateg() {
		return listecateg;
	}

	public void setListecateg(List<Categorie> listecateg) {
		this.listecateg = listecateg;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}
	
	///////

}
