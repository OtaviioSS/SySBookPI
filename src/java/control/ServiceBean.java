package control;

import repository.BookDAO;
import repository.ServiceDAO;
import viewmodel.BookModel;
import viewmodel.ServiceModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named(value = "servicoBean")
@ViewScoped
public class ServiceBean implements Serializable {

    /* Atributos*/
    ServiceDAO servicoDAO;
    BookDAO livroDAO;
    List<ServiceModel> servicos;
    List<String> livro;
    ServiceModel servico;

    /* Construtor */
    public ServiceBean() {
        servicoDAO = new ServiceDAO();
        livroDAO = new BookDAO();
        servicos = null;
        livro = null;
        servico = new ServiceModel();
    }

    @PostConstruct
    public void init() {
        buscar();
        buscarAcoes();
    }

    /* Métodos */
    public void buscar() {
        try {
            servicos = servicoDAO.buscar();
        } catch (Exception e) {
            addMessage(e.getMessage());
        }
    }

    public void salvar() {
        try {
            servicoDAO.salvar(servico);
            RequestContext.getCurrentInstance().execute("PF('dl_registrar').hide();");
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            RequestContext.getCurrentInstance().execute("PF('dl_registrar').hide();");
            return;
        }
        // fechar a janela do modal
        RequestContext.getCurrentInstance().execute("PF('dl_registrar').hide();");

        addMessage("Serviço cadastrado");

        buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable

        servico = new ServiceModel(); // reinicializa para a próxima inserção
    }

    public void buscarAcoes() {
        try {
            List<BookModel> acoes = livroDAO.buscar();
            livro = new ArrayList<String>();

            for (int i = 0; i < acoes.size(); i++) {
                BookModel acao = acoes.get(i);
                String code = acao.getCode();
                livro.add(code);
            }

        } catch (Exception ex) {
            addMessage(ex.getMessage());
        }
    }

    public void addMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        atualizarComponente("msg"); // atualiza o componente de mensagens 
    }

    public void atualizarComponente(String id) {
        RequestContext.getCurrentInstance().update(id);
    }

    public void prepararExclusao(ServiceModel serviceModel) {
        this.servico = serviceModel;
    }

    public void deletar() {
        try {
           this.servicoDAO.deletar(this.servico);
        } catch (Exception e) {
            addMessage(e.getMessage());
            
        }
        addMessage("Servico removido!");
        buscar();
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
      
    }

    /* Getters & Setters */

    public List<ServiceModel> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServiceModel> servicos) {
        this.servicos = servicos;
    }

    public List<String> getCodesAcao() {
        return livro;
    }

    public void setCodesAcao(List<String> codesAcao) {
        this.livro = codesAcao;
    }

    public ServiceModel getServico() {
        return servico;
    }

    public void setServico(ServiceModel servico) {
        this.servico = servico;
    }

    public String getDataInicioFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return sdf.format(this.getDataInicio());
    }

    private Object getDataInicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
