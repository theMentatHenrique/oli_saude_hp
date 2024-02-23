package br.com.henrique.olisaude.oliSaude.DTO;

import br.com.henrique.olisaude.oliSaude.Enum.Sexo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ClientDTO {
        @NotBlank
        String name;
        @Enumerated(EnumType.STRING)
        Sexo sexo;
        List<HealthProblemDTO> healthProblems;
}

