package br.com.henrique.olisaude.oliSaude.Model;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity(name = "health_problem")
@Data
public class HealthProblem {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    private String name;

    @Max(value = 3, message = "The rank must be at most 2.")
    @Min(value = 1, message = "The rank must be greater than 0.")
    private int level;

    @ManyToMany
    @JoinTable(name = "client_health_problem",
            joinColumns = @JoinColumn(name = "health_problem_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    List<Client> clients;
}
