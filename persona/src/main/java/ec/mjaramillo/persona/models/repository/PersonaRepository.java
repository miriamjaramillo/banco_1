package ec.mjaramillo.persona.models.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.mjaramillo.persona.model.entity.Persona;

@Repository
@Primary
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	public Persona findOneByIdentificacion(String identificacion);
}
