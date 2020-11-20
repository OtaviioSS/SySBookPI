/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import repository.BookDAO;
import viewmodel.BookModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LivroDAOTest {

    public LivroDAOTest() {
    }

    @Test
    public void testBuscar() throws Exception {
        BookDAO instance = new BookDAO();
        


        // instancia o objeto da classe a ser testada
        try {
            List<BookModel> livros = instance.buscar();
            
            for(int i=0;i<livros.size();i++){
                BookModel livro = livros.get(i);
                System.out.println("id:"+livro.getId()+" - code:"+livro.getCode()+" - name:"+livro.getName() );
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail(ex.getMessage());
        }
    }

}
