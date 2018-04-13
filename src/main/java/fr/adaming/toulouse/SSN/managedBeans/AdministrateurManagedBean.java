package fr.adaming.toulouse.SSN.managedBeans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import javax.faces.application.FacesMessage;

import fr.adaming.toulouse.SSN.model.Administrateur;
import fr.adaming.toulouse.SSN.model.Produit;
import fr.adaming.toulouse.SSN.service.IAdministrateurService;
import fr.adaming.toulouse.SSN.service.IProduitService;

@ManagedBean(name = "adMB")
@RequestScoped
public class AdministrateurManagedBean {

	// Attributs
	@ManagedProperty(value="#{adService}")
	private IAdministrateurService adminService;

	private Administrateur administrateur;

	// TODO quand Produit et pret
	private IProduitService prService;

	private Produit pr;

	public AdministrateurManagedBean() {
		this.administrateur = new Administrateur();
	}

	public String seConnecter() {

		try {

			Administrateur adOut = adminService.isExist(this.administrateur);

			// Ajouter l'administrateur comme attribut de la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adOut);

			return "accueilCategorie";

		} catch (Exception ex) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'identifiant ou le mot de passe est incorrect"));

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

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}
	
	///////

}
