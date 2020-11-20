/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class ServiceModel implements Serializable{
    
    /* Atributos */
    private Integer id;
  
    private String tiposervico;
    private String livro;
    private String cpfcliente;
    private String dataemprestimo;
    private String datadevolucao;
    
    /* Construtor */
    public ServiceModel(){
    }
    
    /* Métodos */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTiposervico() {
        return tiposervico;
    }

    public void setTiposervico(String tiposervico) {
        this.tiposervico = tiposervico;
    }



    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getCpfcliente() {
        return cpfcliente;
    }

    public void setCpfcliente(String cpfcliente) {
        this.cpfcliente = cpfcliente;
    }

    public String getDataemprestimo() {
        return dataemprestimo;
    }

    public void setDataemprestimo(String dataemprestimo) {
        this.dataemprestimo = dataemprestimo;
    }

    public String getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(String datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceModel other = (ServiceModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
