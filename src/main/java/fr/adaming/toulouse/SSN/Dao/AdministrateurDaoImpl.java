package fr.adaming.toulouse.SSN.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.toulouse.SSN.model.Administrateur;

@Repository
public class AdministrateurDaoImpl implements IAdministateurDao {

	@Autowired
	private SessionFactory sf;
	
	
	//Setter pour l'injection des dépendances
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	private Session s;



	@Override
	public Administrateur isExist(Administrateur ad) {
		//Requête HQL
		String req="FROM Administrateur AS ad WHERE ad.mail=:pMail AND ad.mdp=:pMdp";
		//Ouverture de la session
		s=sf.getCurrentSession();
		//Création du query
		Query query=s.createQuery(req);
		//Passage des paramètres
		query.setParameter("pMail", ad.getMail());
		query.setParameter("pMdp", ad.getMdp());
		return (Administrateur) query.uniqueResult();
	}
	
	
}
