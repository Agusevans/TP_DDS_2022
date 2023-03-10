package domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@MappedSuperclass
public class EntidadPersistente {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}