package fr.adaming.toulouse.SSN.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {
	// transformation de l'association UML en Java
	@ManagedProperty(value="#{catService}")
	private ICategorieService catServ;


	public void setCatServ(ICategorieService catServ) {
		this.catServ = catServ;
	}

	// attributs pour la vue
	private Categorie categorie;
	private HttpSession maSession;
	private List<Categorie> listeCat;
	private UploadedFile uf;
	

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	@PostConstruct
	public void init() {
		// recuperer la liste des categories
		this.listeCat = catServ.getAllCategoriesService();
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// ajouter la liste des categories dans la session
		maSession.setAttribute("listeCategorie", listeCat);
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCat",
		// this.listeCat);
	}

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCat() {
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}
	
	//methodes metier
	public String ajouterCategorie() {
		// ajouter la photo dans l'objet a ajouter
		categorie.setPhoto(this.uf.getContents());

		Categorie catOut = catServ.addCategorieService(this.categorie);

		if (catOut.getIdCategorie() != 0) {
			// recuperer la nouvelle liste
			List<Categorie> liste=catServ.getAllCategoriesService();
			// mettre a jour
			this.listeCat = liste;

			return "accueilCategorie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La categorie n'a pas été ajoutée!!"));
			return "ajouterCategorie";
		}
	}

	public String modifierCategorie() {
		categorie.setPhoto(this.uf.getContents());
		
		int verif=catServ.updateCategorieService(this.categorie);
		
		if (verif != 0) {
			// recuperer la liste de clients
			List<Categorie> liste= catServ.getAllCategoriesService();

			// metre a jour la liste dans la liste
			this.listeCat = liste;
			return "accueilCategorie";
		} else {
			// le messag een cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit n'est pas modifier"));
			return "modifierCategorie";
		}
	}

	public String supprimerCategorie() {
		int verif = catServ.deleteCategorieService(categorie);
		if (verif != 0) {
			List<Categorie> liste= catServ.getAllCategoriesService();
			this.listeCat = liste;
			return "accueilCategorie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La catégorie n'a pas été supprimée!!"));
			return "supprimerCategorie";
		}
	}
	
	
	
	
}
