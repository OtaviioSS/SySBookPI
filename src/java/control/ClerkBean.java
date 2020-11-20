/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import repository.ClerkDAO;
import viewmodel.ClerkModel;
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
@Named(value = "atendenteBean")
@ViewScoped 
public class ClerkBean implements Serializable{
    
    ClerkDAO atendenteDAO;
    List<ClerkModel> atendentes;
    ClerkModel atendente;
    
    public ClerkBean(){
     atendenteDAO = new ClerkDAO();
     atendentes = null;
     atendente = new ClerkModel();
    }
    
    
    @PostConstruct
    public void init(){
        buscar();
    }
    
    /* Métodos */
    public void buscar(){
        try {
            atendentes = atendenteDAO.buscar();
        } catch (Exception ex) {
         System.out.println(ex.getMessage());
        }
    }
    
     public void salvar(){
        try {
            atendenteDAO.salvar(atendente);
           RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            RequestContext.getCurrentInstance().execute("PF('dl_adicionar').hide();");
            return;
        }
        // fechar a janela do modal
        RequestContext.getCurrentInstance().execute("PF('dl_registrar2').hide();");
        
        addMessage("Atendente adicionada com sucesso!");
        
        buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
        
        atendente = new ClerkModel(); // reinicializa para a próxima inserção
    }
        public void deletar(ClerkModel atendente) {
        try {
            atendenteDAO.deletar(atendente);
        } catch (Exception e) {
            addMessage(e.getMessage());
            return;
        }
        addMessage("Atendente removido");

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
    
       public void onRowEdit(RowEditEvent event) { // invocado ao editar um registro
        atendente = (ClerkModel) event.getObject();
        try {
            atendenteDAO.salvar(atendente);
        } catch (Exception ex) {
            addMessage(ex.getMessage());
            return;
        }
        addMessage("Ação " + atendente.getNomeAtendente() + " editada com sucesso!");

        //buscar(); // após inserir a nova ação, precisamos buscar novamente as informações no BD
        //atualizarComponente("form"); // atualizo o form, para dar um refresh no datatable
        atendente = new ClerkModel(); // reinicializa para a próxima inserção

    }
       
        public void onRowCancel(RowEditEvent event) { // invocado ao cancelar a edição de um registro
        addMessage("Edição cancelada!");
    }
    
    /* Getters & Setters */

    public List<ClerkModel> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<ClerkModel> atendentes) {
        this.atendentes = atendentes;
    }

    
     public ClerkModel getAtendente() {
        return atendente;
    }

    public void setAtendente(ClerkModel atendente) {
        this.atendente = atendente;
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
