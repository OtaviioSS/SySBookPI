/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

public class Cloud {
    private static Cloud instance = null;
    String IMAGEM;
    
    private Cloud() {
    }
    
    public static Cloud getInstance(){
        if(instance==null){
            instance = new Cloud();
        }
        return instance;
    }

    public String getIMAGEM() {
        return IMAGEM;
    }

    public void setIMAGEM(String IMAGEM) {
        this.IMAGEM = IMAGEM;
    }
    
    
    
    
}
