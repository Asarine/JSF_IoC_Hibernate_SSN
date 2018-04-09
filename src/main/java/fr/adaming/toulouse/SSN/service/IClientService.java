package fr.adaming.toulouse.SSN.service;

import fr.adaming.toulouse.SSN.model.Client;

public interface IClientService {
	
	public Client isExist(Client cl);

	public Client addClient(Client cl);

	public int modifClient(Client cl);

	public int deleteClient(Client cl);

}
