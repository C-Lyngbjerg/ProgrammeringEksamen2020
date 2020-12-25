package clb.eksamen.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supervisor_id")
    //for at undgå uendeligt loop, ignoreres parent property i child objekt med JsonBackReference
//    @JsonManagedReference
    @JsonBackReference
    private Supervisor supervisor;

    public Student() {
    }

    public Student(long id, String name, String email, Supervisor supervisor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supervisor = supervisor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public long getSupervisor_id() {
        return supervisor.getId();
    }

    public String getSupervisor_name() {
        return supervisor.getName();
    }

}
