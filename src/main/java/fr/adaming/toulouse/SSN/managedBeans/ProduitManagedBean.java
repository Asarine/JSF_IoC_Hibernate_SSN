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
import fr.adaming.toulouse.SSN.model.Produit;
import fr.adaming.toulouse.SSN.service.IProduitService;

@ManagedBean(name="prodMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	//transforamtion de l'association uml en java 
	
	@ManagedProperty(value="#{prodService}")
	private IProduitService prodService;

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}
	
	//attributs pour la vue
    private Produit produit;
	private Categorie categorie;
	private HttpSession maSession;
	private List<Produit> listeProduits;
	private Boolean indice;
	private UploadedFile uf;

	
	
	//constructeur vide
	public ProduitManagedBean() {
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.indice = false;
	}
	
	@PostConstruct
	public void init() {
		
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeProduits = prodService.getAllProduitsService();
		//ajout de la liste dans la session
		maSession.setAttribute("prodListe", listeProduits);

	}
	// get et set
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Boolean getIndice() {
		return indice;
	}

	public void setIndice(Boolean indice) {
		this.indice = indice;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	public IProduitService getProdService() {
		return prodService;
	}
	
	//methodes metier
	public String ajouterProduit(){
		//ajouter la photo dans l'objet 
		produit.setPhoto(this.uf.getContents());
		 Produit prodAjout=prodService.ajouterProduitService(this.produit,this.categorie);
		
		 if (prodAjout.getIdProduit() != 0) {

				// recuperer la liste de clients
				List<Produit> liste = prodService.getAllProduitsService();

				// metre a jour la liste dans la liste
				this.listeProduits = liste;
				return "accueilProduit";
			} else {

				// le message en cas dechec
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de produit est echoue"));
				return "ajouterProduit";

			}
		}
		
	public String deleteProduit(){
		int verif=prodService.deleteProduitService(this.produit);
		if(verif !=0){
			//recuperer la liste de produits
			List<Produit> liste=prodService.getAllProduitsService();
			
			//mettre a jour la liste dans la session 
			this.listeProduits=liste;
			return "accueilProduit";
		}else{
			
	   //le message en cas echec 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression de produit est echoue"));
			return "deleteProduit";
			
			
		}
	}
	  
  public String modifierProduit(){
	  int verif=prodService.updateProduitService(this.produit,this.categorie);
	  if(verif !=0){
		  //recuperre la liste de produits 
		  List<Produit> liste=prodService.getAllProduitsService();
		  //mettre a jour la liste dans la session 
		  this.listeProduits=liste;
		  return "accueilProduit";
		  
	  }else{
		  //le message en cas echec
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modifictaion de produit est echoue"));
		  return "modifierProduit";
	  }
	  
	  
	  
  }
	
	
	   
	
	
	}
	
	
	
	
	
	
	
