package prjVendaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProdutoDAO {
	
	private EntityManager conexao = null;
	
	public ProdutoDAO(EntityManagerFactory emf) {
		
		conexao = emf.createEntityManager();
		
	}
	
	public void adicionar(Produto p) {
		
		conexao.getTransaction().begin();
		conexao.persist(p);
		conexao.getTransaction().commit();
		
	}
	
	public void remover(Produto p) {
		
		conexao.getTransaction().begin();
		conexao.remove(p);
		conexao.getTransaction().commit();
	}
	
	public Produto consular(int id) {
		
		return conexao.find(Produto.class, id);
		
	}
	
	

}
