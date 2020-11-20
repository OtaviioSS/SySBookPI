package control;

import repository.BookDAO;
import viewmodel.BookModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

@Named(value = "livroBean")
@ViewScoped // Esse bean será mantido até que o usuário mude de tela
public class BookBean implements Serializable {

    BookDAO livroDAO;
    List<BookModel> livros;
    BookModel livro;

    public BookBean() {
        livroDAO = new BookDAO();
        livros = null;
        livro = new BookModel();
    }

    @PostConstruct
    public void init() { // como se fosse u  segundo contrutor
        buscar();
    }

    public void buscar() {
        try {
            livros = livroDAO.buscar(); // inicializa a fonte de dados com as informações advindas do BD
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void salvar() {
        try {
            livroDAO.salvar(livro);
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");
            return;
        }
        // fechar a janela do modal
        RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");

        addMessage("Ação adicionada com sucesso!");

        buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable

        livro = new BookModel(); // reinicializa para a próxima inserção
    }

    public void deletar(BookModel acao) {
        try {
            livroDAO.deletar(acao);
        } catch (Exception e) {
            addMessage(e.getMessage());
            return;
        }
        addMessage("Ação removida com sucesso!");

        buscar();
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
    }

    public void onRowEdit(RowEditEvent event) { // invocado ao editar um registro
        livro = (BookModel) event.getObject();
        try {
            livroDAO.salvar(livro);
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            return;
        }
        addMessage("Ação " + livro.getCode() + " editada com sucesso!");

        //buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        //atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
        livro = new BookModel(); // reinicializa para a próxima inserção

    }

    public void onRowCancel(RowEditEvent event) { // invocado ao cancelar a edição de um registro
        addMessage("Edição cancelada!");
    }

    public void atualizarComponente(String id) {
        RequestContext.getCurrentInstance().update(id);
    }

    public void addMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        atualizarComponente("msg"); // atualiza o componente de mensagens 
    }

    // getters & setters
    public List<BookModel> getLivros() {
        return livros;
    }

    public void setLivros(List<BookModel> livros) {
        this.livros = livros;
    }

    public BookModel getLivro() {
        return livro;
    }

    public void setLivro(BookModel livro) {
        this.livro = livro;
    }

}
