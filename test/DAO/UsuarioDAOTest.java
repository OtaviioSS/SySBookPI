package DAO;

import repository.UserDAO;
import viewmodel.UserModel;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }
    
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        Integer id = 1;
        UserDAO instance = new UserDAO();

        try{
            UserModel usuario = instance.buscar(id);
            if(usuario==null){
                 fail("Sem resultados!");
            }
            System.out.println("id: "+usuario.getId());
            System.out.println("login: "+usuario.getLogin());
            System.out.println("name: "+usuario.getName());
            System.out.println("password: "+usuario.getPassword());
            System.out.println("Photo: "+Arrays.toString(usuario.getPhoto()));
        }catch(Exception e){
            fail(e.getMessage());
        }

    }
    
}
