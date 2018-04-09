package fr.adaming.toulouse.SSN.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.toulouse.SSN.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao {

	@Autowired
	private SessionFactory sf;
	
	//Setter pour l'injection des dépendances
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	private Session s;

	@Override
	public Client isExist(Client cl) {
		//Requête HQL
		String req="FROM Client AS cl WHERE cl.email=:pEmail AND cl.mdp=:pMdp";
		//Ouverture de la session
		s=sf.getCurrentSession();
		//Création du query
		Query query=s.createQuery(req);
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pMdp", cl.getMdp());
		return (Client) query.uniqueResult();
	}

	@Override
	public Client addClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

}
