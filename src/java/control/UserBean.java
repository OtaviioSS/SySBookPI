package control;

import repository.UserDAO;
import viewmodel.UserModel;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Cloud;

@Named(value = "usuarioBean")
@ViewScoped
public class UserBean implements Serializable {
  /* Atributos */
    UserDAO usuarioDAO;
    UserModel usuario;
    String IMAGEM;
    
    /* Construtor */
    public UserBean() {
        usuarioDAO = new UserDAO();
        usuario = new UserModel();
        Cloud.getInstance().setIMAGEM("https://cdn.discordapp.com/attachments/692016312389599293/764295249563877396/Sysbook.png");
    }
    
    @PostConstruct
    public void init(){
        IMAGEM = Cloud.getInstance().getIMAGEM();
    }
    
    /* Métodos */
    
    public void logar(){
        boolean login = false;
        
        // conectar com o banco e retornar o resultado da verificação
        try{
            login = usuarioDAO.login(usuario);
        } catch (Exception ex) {
                addMessage(ex.getMessage());
                return;
         }
        
        if(login == true){
            addMessage("Dados corretos!");
            FacesContext context = FacesContext.getCurrentInstance();
            try {
                context.getExternalContext().redirect("index.jsf");
            } catch (IOException ex) {
                addMessage(ex.getMessage());
                
            }
        }else{
            addMessage("Senha errada!");
        }
    }
    
     public void addMessage(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        atualizarComponente("msg"); // atualiza o componente de mensagens 
    }
    public void atualizarComponente(String id){
         RequestContext.getCurrentInstance().update(id);
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public String getIMAGEM() {
        return IMAGEM;
    }

    public void setIMAGEM(String IMAGEM) {
        this.IMAGEM = IMAGEM;
    }
    
    
    
}
