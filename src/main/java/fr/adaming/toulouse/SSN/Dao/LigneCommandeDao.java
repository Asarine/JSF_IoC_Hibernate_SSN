package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.toulouse.SSN.model.LigneCommande;
import fr.adaming.toulouse.SSN.model.Produit;

public class LigneCommandeDao implements ILigneCommandeDao {

	// Injection de dependance

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection de depndance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	// Attributs
	Session s;
	Query q;

	@Override
	public List<LigneCommande> getAllLigneCommandesDao(Produit prod) {
		String req = "From LigneCommande where lcom where lcom.prod.id=:pId";
		// ouvrir une session
		s = sf.getCurrentSession();

		// recuperer le query
		q = s.createQuery(req);

		// passage de la param
		q.setParameter("pId", prod.getIdProduit());

		return q.list();
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lcom) {
		// ouvrir une session
		s = sf.getCurrentSession();

		// faire persister
		s.save(lcom);

		// recuperer le produit
		return lcom;

	}

	@Override
	public int deleteLigneCommande(LigneCommande lcom) {
		String req = "DELETE LigneCommande lc lcom.id=:pId and lcom.prod.id=:pIdProd";
		// récup query
		Query query = s.createQuery(req);

		// passage des params
		q.setParameter("pId", lcom.getIdLComm());
		q.setParameter("pIdProd",lcom.getProd().getIdProduit());
		return q.executeUpdate();
	}

}
