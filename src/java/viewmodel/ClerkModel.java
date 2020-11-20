/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

/**
 *
 * @author Otavio
 */
public class ClerkModel {
    private Integer atendenteid;
    
    private String cpfatendete;
    private String nomeAtendente;
    private String matriculaAtendente;
    private String cargoAtendente;
    
    
    public ClerkModel(){
        
    }
    
    
    

    public Integer getAtendenteid() {
        return atendenteid;
    }

    public void setAtendenteid(Integer atendenteid) {
        this.atendenteid = atendenteid;
    }

    public String getCpfatendete() {
        return cpfatendete;
    }

    public void setCpfatendete(String cpfatendete) {
        this.cpfatendete = cpfatendete;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getMatriculaAtendente() {
        return matriculaAtendente;
    }

    public void setMatriculaAtendente(String matriculaAtendente) {
        this.matriculaAtendente = matriculaAtendente;
    }

    public String getCargoAtendente() {
        return cargoAtendente;
    }

    public void setCargoAtendente(String cargoAtendente) {
        this.cargoAtendente = cargoAtendente;
    }

    public void add(ClerkModel atendente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
