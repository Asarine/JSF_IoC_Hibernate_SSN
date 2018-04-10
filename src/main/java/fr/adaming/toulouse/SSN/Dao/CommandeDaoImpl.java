package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.toulouse.SSN.model.Client;
import fr.adaming.toulouse.SSN.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {
	// Injection de dépendance
	@Autowired
	private SessionFactory sf;

	// setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	// Attributs
	Session s;
	Query q;

	@Override
	public List<Commande> getAllCommandesDao(Client cl) {
		String req = "SELECT com FROM Commande as com where com.client.id=:pId";
		// ouvrir une session
		s = sf.getCurrentSession();
		// recuperer le query
		q = s.createQuery(req);
		//passage des parameters 
		q.setParameter("pId", cl.getIdClient());

		return q.list();
	}

	@Override
	public Commande addCommande(Commande com) {
		// Ouvrir une session
		s = sf.getCurrentSession();
		// Faire persister la commande
		s.save(com);
		// Récupérerla commande avec son nouvel id
		return com;

	}

	@Override
	public int deleteCommande(Commande com) {
		String req = "DELETE FROM Commande com WHERE com.id=:pIdCom ";
		// ouvrir une session
		s = sf.getCurrentSession();

		// récup query
		Query query = s.createQuery(req);
		// passage de params
		query.setParameter("pIdCom", com.getIdCommande());

		return query.executeUpdate();
	}

}
