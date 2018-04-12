package fr.adaming.toulouse.SSN.Dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import fr.adaming.toulouse.SSN.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

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
	public List<Categorie> getAllCategoriesDao() {
		// requete hql
		String req = "FROM Categorie";

		// ouvrir une session
		s = sf.getCurrentSession();

		// recuperer le query
		q = s.createQuery(req);

		// chargement des photos
		List<Categorie> listeOut = q.list();
		for (Categorie cat : listeOut) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return listeOut;

	}

	@Override
	public Categorie addCategorieDao(Categorie cat) {
		// Ouvrir une session
		s = sf.getCurrentSession();
		// Faire persister l'étudiant e
		s.save(cat);
		// Récupérer l'étudiant avec son nouvel id
		return cat;
	}

	@Override
	public int updateCategorieDao(Categorie cat) {
		String req = "UPDATE Categorie cat set cat.description=:pDescription,cat.nomCategorie=:pNomCategorie,cat.photo=:pPhoto WHERE cat.id=:pIdCategorie";
		// ouvrir une session
		s = sf.getCurrentSession();

		// récup query
		Query query = s.createQuery(req);

		query.setParameter("pDescription", cat.getDescription());
		query.setParameter("pNomCategorie", cat.getNomCategorie());
		query.setParameter("pPhoto", cat.getPhoto());
		query.setParameter("pIdCategorie", cat.getIdCategorie());

		return query.executeUpdate();
	}

	@Override
	public int deleteCategorieDao(Categorie cat) {
		String req = "DELETE Categorie cat WHERE cat.idCategorie=:pId";
		// ouvrir une session
		s = sf.getCurrentSession();

		// récup query
		Query query = s.createQuery(req);

		// passage des params
		q.setParameter("pId", cat.getIdCategorie());
		return query.executeUpdate();
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		// requete hql
		String req = "FROM Categorie cat WHERE cat.idCategorie=:pId";

		// ouvrir une session
		s = sf.getCurrentSession();

		// Récupérer le query
		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", cat.getIdCategorie());

		return (Categorie) query.uniqueResult();
	}

}
