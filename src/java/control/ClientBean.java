/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import repository.ClientDAO;
import viewmodel.ClientModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Otavio
 */
@Named(value = "clienteBean")
@ViewScoped 
public class ClientBean implements Serializable{
        
    ClientDAO clienteDAO;
    List<ClientModel> clientes;
    ClientModel cliente;
    
    public ClientBean(){
     clienteDAO = new ClientDAO();
     clientes = null;
     cliente = new ClientModel();
    }
    
    
    @PostConstruct
    public void init(){
        buscar();
    }
    
    /* Métodos */
    public void buscar(){
        try {
            clientes = clienteDAO.buscar();
        } catch (Exception ex) {
         System.out.println(ex.getMessage());
        }
    }
    
     public void salvar(){
        try {
            clienteDAO.salvar(cliente);
           RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");
            return;
        }
        // fechar a janela do modal
        RequestContext.getCurrentInstance().execute("PF('dl_registrar2').hide();");
        
        addMessage("Dados do cliente adicionado");
        
        buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
        
        cliente = new ClientModel(); // reinicializa para a próxima inserção
    }
        public void deletar(ClientModel cliente) {
        try {
            clienteDAO.deletar(cliente);
        } catch (Exception e) {
            addMessage(e.getMessage());
            return;
        }
        addMessage("Cliente removido!");

        buscar();
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
    }
    
     public void addMessage(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        atualizarComponente("msg"); // atualiza o componente de mensagens 
    }
    public void atualizarComponente(String id){
         RequestContext.getCurrentInstance().update(id);
    }
    
       public void onRowEdit(RowEditEvent event) { 
        cliente = (ClientModel) event.getObject();
        try {
            clienteDAO.salvar(cliente);
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            return;
        }
        addMessage("Dados do cliente " + cliente.getNome()+ " editados");

        cliente = new ClientModel();

    }
       
        public void onRowCancel(RowEditEvent event) { 
        addMessage("Edição cancelada!");
    }
    
    /* Getters & Setters */

    public List<ClientModel> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClientModel> clientes) {
        this.clientes = clientes;
    }

    
     public ClientModel getCliente() {
        return cliente;
    }

    public void setAtendente(ClientModel cliente) {
        this.cliente = cliente;
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
