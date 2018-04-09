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
import fr.adaming.toulouse.SSN.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {
	
	//Attributs
		private Client client;
		private String mdp;
		HttpSession maSession;
	
	@ManagedProperty(value="#{clService}")
	private IClientService clientService;

	

	/**
	 * @param clientService
	 */
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	//Constructeur vide du MB
	public ClientManagedBean() {
		super();
		this.client = new Client();
		this.mdp=new String();
	}
	
	@PostConstruct
	public void init(){
		
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

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

	public String seConnecter(){
		try{
			this.client=clientService.isExist(client);
			//Ajouter le client à la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", client);
			return "accueilClient";
		}catch (Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Identifiant ou mot de passe incorrect"));
		}
		return "seconnecterClient";
	}
}
