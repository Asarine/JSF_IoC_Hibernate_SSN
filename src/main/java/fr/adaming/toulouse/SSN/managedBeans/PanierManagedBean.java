package fr.adaming.toulouse.SSN.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Panier;
import fr.adaming.toulouse.SSN.model.Produit;
import fr.adaming.toulouse.SSN.service.ICommandeService;
import fr.adaming.toulouse.SSN.service.IProduitService;

@ManagedBean(name = "panMB")
@RequestScoped
public class PanierManagedBean implements Serializable {

	// attributs
	private Panier panier;
	private HttpSession maSession;
	private LigneCommande lcom;
	private double montantTotal;
	private Produit produit;
	private List<LigneCommande> listeLC;
	private int quantite;

	@ManagedProperty(value = "#{prodService}")
	private IProduitService prodServ;

	@ManagedProperty(value = "#{comService}")
	private ICommandeService comServ;

	// setter pour l'injection des dependance
	public void setProdServ(IProduitService prodServ) {
		this.prodServ = prodServ;
	}

	public void setComServ(ICommandeService comServ) {
		this.comServ = comServ;
	}

	public PanierManagedBean() {
		this.panier = new Panier();
		this.produit = new Produit();
		this.lcom = new LigneCommande();
	}

	@PostConstruct
	public void init() {
		// recuperer la session
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		// ajouter le panier dans la session
		maSession.setAttribute("panierSession", panier);
		// initialisation de la liste de lignes de commandes
		this.listeLC = new ArrayList<LigneCommande>();
		// enregistrement de la liste des lignes de commandes dans la session
		maSession.setAttribute("lcomListe", listeLC);
		// initialisation du montant total du panier
		montantTotal = 0;
		// enregistrement du montant total dans la session
		maSession.setAttribute("total", montantTotal);
		// intialisation de la quantite
		this.quantite = 0;
		// ajouter la quantite dans la session
		maSession.setAttribute("quantiteSession", quantite);
	}

	// get et set
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public LigneCommande getLcom() {
		return lcom;
	}

	public void setLcom(LigneCommande lcom) {
		this.lcom = lcom;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<LigneCommande> getListeLC() {
		return listeLC;
	}

	public void setListeLC(List<LigneCommande> listeLC) {
		this.listeLC = listeLC;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public IProduitService getProdServ() {
		return prodServ;
	}

	public ICommandeService getComServ() {
		return comServ;
	}

	//methodes metier
public String ajouterLCdansPanier(){
		
		
		//recuperer le produit dans la session
		Produit pOut = (Produit) maSession.getAttribute("produitSession");
		int qtDisponible = pOut.getQuantite();
		//verifier que la quantite demandee est disponible
		if(this.quantite<=qtDisponible){
			//creation d'une ligne de commande
			LigneCommande lcIn = new LigneCommande();
			lcIn.setProd(pOut);
			lcIn.setQuantite(quantite);
			double prixLigne = pOut.getPrix()*quantite;
			lcIn.setPrix(prixLigne);
			//ajout de la ligne de commande dans la liste du panier
			this.listeLC.add(lcIn);
			//mise a jour de la liste dans la session
			maSession.setAttribute("lcomListe", listeLC);
			//modifier le montant total du panier et mise a jour dans la session
			this.montantTotal = montantTotal + prixLigne;
			maSession.setAttribute("total", montantTotal);
			return "panier.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la quantite maximale disponible est de: "+qtDisponible));
			return "voirProduitSeul.xhtml";
		}
		
	}
	
	
	
}
