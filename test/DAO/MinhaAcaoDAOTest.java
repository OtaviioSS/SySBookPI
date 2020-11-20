
package DAO;

import repository.ServiceDAO;
import viewmodel.ServiceModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MinhaAcaoDAOTest {
    
    public MinhaAcaoDAOTest() {
    }
    
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        Integer id = 2;
        ServiceDAO instance = new ServiceDAO();

        try{
            List<ServiceModel> result = instance.buscar();
            if(result==null){
                fail("nenhum dado retornado!");
            }
            for(int i=0;i<result.size();i++){
                ServiceModel minhaAcao = result.get(i);
                System.out.print(minhaAcao.getId());
                System.out.print(", "+minhaAcao.getCpfcliente());
                System.out.print(", "+minhaAcao.getDatadevolucao());
                System.out.println(", "+minhaAcao.getDataemprestimo());
                System.out.println(", "+minhaAcao.getLivro());
                System.out.println(", "+minhaAcao.getTiposervico());
            }
        }catch(Exception e){
            fail(e.getMessage());
        }
        

        
    }
    
}
