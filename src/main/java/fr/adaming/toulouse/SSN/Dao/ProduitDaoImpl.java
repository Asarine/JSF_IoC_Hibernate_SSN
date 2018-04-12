package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.toulouse.SSN.model.Categorie;
import fr.adaming.toulouse.SSN.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	// Injection de dépendance
	@Autowired
	private SessionFactory sf;

	// setter pour linjection de dependance

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	// Attributs
	Session s;
	Query q;

	@Override
	public List<Produit> getAllProduitsDao(Categorie cat) {
		// requete hql
		String req = "FROM Produit WHERE prod.categorie.id=:pId";
		// ouvrir une session
		s = sf.getCurrentSession();

		// recuperer le query
		q = s.createQuery(req);

		// passage de la param
		q.setParameter("pId", cat.getIdCategorie());

		// chargement des photos
		List<Produit> listeOut = q.list();
		for (Produit prod : listeOut) {
			prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
		}
		return listeOut;
	}

	@Override
	public Produit ajouterProduitDao(Produit prod) {
		
		//ouvrir une session
		s = sf.getCurrentSession();

	   // faire persister
		s.save(prod);
		
		//recuperer le produit 
		return prod;
	}

	@Override
	public int updateProduitDao(Produit prod) {
		String req = "UPDATE Produit prod set  prod.description=:pDescription,prod.designation=:pDesignation,prod.prix=:pPrix,prod.photo=:pPhoto,prod.quantite=:pQuantite WHERE prod.id=:pId and prod.cat.id=:pIdCategorie";
		// ouvrir une session
		s = sf.getCurrentSession();

		// récup query
		Query query = s.createQuery(req);

		// passage de la param
		query.setParameter("pDescription", prod.getDescription());
		query.setParameter("pDesignation", prod.getDesignation());
		query.setParameter("pPrix", prod.getPrix());
		query.setParameter("pPhoto", prod.getPhoto());
		query.setParameter("pId", prod.getIdProduit());
		query.setParameter("pIdCategorie", prod.getCat().getIdCategorie());
		query.setParameter("pQuantite", prod.getQuantite());

		return query.executeUpdate();
	}

	@Override
	public int deleteProduitDao(Produit prod) {
		String req = "DELETE Produit prod WHERE prod.idProduit=:pId";
		// ouvrir une session
		s = sf.getCurrentSession();

		// passage de la param
		q.setParameter("pId", prod.getIdProduit());

		// recuprer le query
		Query query = s.createQuery(req);
		
		return query.executeUpdate();
	}

	@Override
	public Produit rechercherProduitDao(Produit prod) {

		// requete hql
		String req = "FROM Produit prod WHERE prod.idProduit=:pId";

		// ouvrir une session
		s = sf.getCurrentSession();

		// passage des params
		q.setParameter("pId", prod.getIdProduit());

		// Récupérer le query
		Query query = s.createQuery(req);

		return (Produit) query.uniqueResult();
	}

	@Override
	public List<Produit> getAllProduitsDao() {
		// requete hql
		String req = "FROM Produit";
		// ouvrir une session
		s = sf.getCurrentSession();

		// recuperer le query
		q = s.createQuery(req);

		// chargement des photos
		List<Produit> listeOut = q.list();
		for (Produit prod : listeOut) {
			prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));
		}
		return listeOut;
	}
}
