package fr.adaming.toulouse.SSN.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.toulouse.SSN.model.Client;
import fr.adaming.toulouse.SSN.service.EnvoyerMail;
import fr.adaming.toulouse.SSN.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	// Attributs
	private Client client;
	private String mdp;
	HttpSession maSession;

	@ManagedProperty(value = "#{clService}")
	private IClientService clientService;

	// Setter pour l'injection des dépendances
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	// Constructeur vide du MB
	public ClientManagedBean() {
		super();
		this.client = new Client();
		this.mdp = new String();
	}

	@PostConstruct
	public void init() {

		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String seConnecter() {
		try {
			
			//Vérifier que le client est bien dans la base de données
			this.client = clientService.isExist(this.client);
			// Ajouter le client à la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", this.client);
			return "accueilClient";
		} catch (Exception ex) {
			//Création d'un message d'erreur si le client n'est pas trouvé
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Identifiant ou mot de passe incorrect"));
		}
		return "seconnecterClient";
	}

	public String ajoutClient() {
		//Ajout du client à la base de données
		Client clAjout = clientService.addClient(this.client);
		if (clAjout.getIdClient() != 0) {
			this.client = clAjout;
			//Envoi d'un mail de confirmation
			EnvoyerMail.envoyerMessageAjout(this.client);
			//Création d'un message à afficher pour prévenir le client qu'il recoit son mot de passe
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Votre mot de passe vous a été envoyé par mail"));
			return "seconnecterClient";
		} else {
			//Création d'un message d'erreur si le client n'est pas trouvé
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'a pas été effectué."));
			return "ajoutClient";
		}
	}
	
	public String modifierClient(){
		//On récupère le client de la session afin d'avoir son Id (nécessaire pour la dao)
		Client clIn=(Client) maSession.getAttribute("clientSession");
		//Attribuer l'id du client de la session au client à modifier
		this.client.setIdClient(clIn.getIdClient());
		//Modifier le client
		int verif=clientService.modifClient(this.client);
		if (verif!=0){
			//Modifier le client dans la session
			maSession.setAttribute("clientSession", this.client);
			return "accueilClient";
		}else{
			//Création d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Les modifications n'ont pas été effectuées."));
			return "modifClient";
		}
	}
}
