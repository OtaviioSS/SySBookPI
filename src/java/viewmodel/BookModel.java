
package viewmodel;

import java.util.Objects;

public class BookModel {
    
    /* Atributos */
    private Integer id;
    private String name;
    private String code;
    private String autor;
    
     /* Getters & Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // se o outro objeto é o mesmo
            return true;
        }
        if (obj == null) { // se o outro objeto é nulo
            return false;
        }
        if (getClass() != obj.getClass()) { // se o outro objeto é oriundo de outra classe
            return false;
        }
        
        final BookModel other = (BookModel) obj;
        if (!Objects.equals(this.id, other.id)) { // se o outro objeto possui o id diferente
            return false;
        }
        return true;
    }
    
    
}
