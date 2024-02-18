package br.com.henrique.olisaude.oliSaude.Model;

import br.com.henrique.olisaude.oliSaude.Enum.Sexo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name ="client")
@Data
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String name;

    @CreationTimestamp
    private Date creationDate;

    private Date updateDate;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToMany
    @JoinTable(name = "client_health_problem",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "health_problem_id"))
    private List<HealthProblem> healthProblems;

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }
}