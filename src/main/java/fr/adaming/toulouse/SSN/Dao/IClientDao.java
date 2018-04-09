package fr.adaming.toulouse.SSN.Dao;

import fr.adaming.toulouse.SSN.model.Client;

public interface IClientDao {

	public Client isExist(Client cl);

	public Client addClient(Client cl);

	public int modifClient(Client cl);

	public int deleteClient(Client cl);

}
