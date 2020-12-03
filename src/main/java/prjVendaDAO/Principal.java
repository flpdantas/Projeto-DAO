package prjVendaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class Principal {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vendas");

		EntityManager em = emf.createEntityManager();

		String descricaoOpcao = "1 - Cadastrar \n" + "2 - Editar\n" + "3 - Remover\n" + "4 - Consultar\n" + "5 - Sair";

		int opcao = Integer.parseInt(JOptionPane.showInputDialog(descricaoOpcao));

		switch (opcao) {
		case 1:
			String descricao = JOptionPane.showInputDialog("Informe a descrição");
			String valor = JOptionPane.showInputDialog("Informe o valor");
			String valorCompra = JOptionPane.showInputDialog("Informe o valor de compra");

			try {

				Produto produtoNovo = new Produto();
				produtoNovo.setDescricao(descricao);
				produtoNovo.setValor(Double.parseDouble(valor));
				produtoNovo.setValorCompra(Double.parseDouble(valorCompra));

				em.getTransaction().begin();
				em.persist(produtoNovo);
				em.getTransaction().commit();

				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null,
						"Erro ao cadastrar o produto! Verifique os dados e tente novamente.");
			}
			break;

		case 2:
			int idProdutoEditar = Integer
					.parseInt(JOptionPane.showInputDialog("Informe o id do produto que deseja editar"));

			try {

				Produto produtoEditado = em.find(Produto.class, idProdutoEditar);
				em.detach(produtoEditado);

				String descricaoEditado = JOptionPane.showInputDialog("Informe a descrição");
				String valorEditado = JOptionPane.showInputDialog("Informe o valor");
				String valorCompraEditado = JOptionPane.showInputDialog("Informe o valor de compra");

				produtoEditado.setDescricao(descricaoEditado);
				produtoEditado.setValor(Double.parseDouble(valorEditado));
				produtoEditado.setValorCompra(Double.parseDouble(valorCompraEditado));

				Produto produtoEditadoFinal = em.merge(produtoEditado);

				em.getTransaction().begin();
				em.getTransaction().commit();

				JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao editar o produto! Verifique o id e tente novamente.");
			}
			break;

		case 3:
			int idProdutoExcluir = Integer
					.parseInt(JOptionPane.showInputDialog("Informe o id do produto que deseja excluir"));

			try {

				Produto produtoExcluir = em.find(Produto.class, idProdutoExcluir);
				em.remove(produtoExcluir);

				em.getTransaction().begin();
				em.getTransaction().commit();

				JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir o produto! Verifique o id e tente novamente.");
			}
			break;

		case 4:
			int idProdutoConsultar = Integer
					.parseInt(JOptionPane.showInputDialog("Informe o id do produto que deseja consultar"));

			try {

				Produto produtoConsultar = em.find(Produto.class, idProdutoConsultar);
				em.detach(produtoConsultar);

				em.getTransaction().begin();
				em.getTransaction().commit();

				JOptionPane.showMessageDialog(null,
						"ID do produto: " + produtoConsultar.getId() + 
						"\nNome do produto: " + produtoConsultar.getDescricao() + 
						"\nValor: R$ " + produtoConsultar.getValor() +
						"\nValor de compra: R$ " + produtoConsultar.getValorCompra());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar o produto! Verifique o id e tente novamente.");
			}

		case 5:
			JOptionPane.showMessageDialog(null, "Obrigado e volte sempre.");
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opção Invalida!");
			break;
		}

		
	

		
		
		
		
		/*em.close();
		emf.close();*/
			
	}

}
