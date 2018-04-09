package fr.adaming.toulouse.SSN.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.toulouse.SSN.Dao.IClientDao;
import fr.adaming.toulouse.SSN.model.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	//Setter pour l'injection de dépendances	
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public Client isExist(Client cl) {
		return clientDao.isExist(cl);
	}

	@Override
	public Client addClient(Client cl) {
		//Générer un mot de passe aléatoire et l'attribuer au nouveau client
		String password=GenerationMotDePasse.genererMotDePasse();
		cl.setMdp(password);
		return clientDao.addClient(cl);
	}

	@Override
	public int modifClient(Client cl) {
		return clientDao.modifClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

}
