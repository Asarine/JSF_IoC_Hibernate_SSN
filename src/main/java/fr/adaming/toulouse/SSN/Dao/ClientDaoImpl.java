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
		//Passage des paramètres
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pMdp", cl.getMdp());
		return (Client) query.uniqueResult();
	}

	@Override
	public Client addClient(Client cl) {
		//Ouverture de la session
		s=sf.getCurrentSession();
		//Ajout du client à la base de données
		s.save(cl);
		return cl;
	}

	@Override
	public int modifClient(Client cl) {
		//Requête HQL
		String req="UPDATE Client cl SET cl.nomClient=:pNom, cl.adresse=:pAdresse, cl.codePostal=:pCodepost, cl.ville=:pVille, cl.email=:pMail, cl.tel=:pTel, cl.mdp=:pMdp WHERE cl.idClient=:pId";
		//Ouverture de la session
		s=sf.getCurrentSession();
		//Création du Query
		Query query=s.createQuery(req);
		//Passage des paramètres
		query.setParameter("pNom", cl.getNomClient());
		query.setParameter("pAdresse", cl.getAdresse());
		query.setParameter("pCodepost", cl.getCodePostal());
		query.setParameter("pVille", cl.getVille());
		query.setParameter("pMail", cl.getEmail());
		query.setParameter("pTel", cl.getTel());
		query.setParameter("pMdp", cl.getMdp());
		query.setParameter("pId", cl.getIdClient());
		return query.executeUpdate();
	}

	@Override
	public int deleteClient(Client cl) {
		//Requête HQL
		String req="DELETE Client cl WHERE cl.idClient=:pId";
		//Ouvrir la session
		s=sf.getCurrentSession();
		//Création du query
		Query query=s.createQuery(req);
		query.setParameter("pId", cl.getIdClient());
		return query.executeUpdate();
	}

}
