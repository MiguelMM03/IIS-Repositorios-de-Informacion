package uo.ri.cws.application.persistence.mechanic;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

public interface MechanicGateway extends Gateway<MechanicDALDto> {

	   /**
     * Find mechanics by their DNIs
     * @param dni Mechanics DNIs
     * @return A list of mechanics
     */
    Optional<MechanicDALDto> findByDni(String dni);

    public class MechanicDALDto {

	public String id;
	public Long version;

	public String dni;
	public String name;
	public String surname;

    }

    /**
     * Find mechanics by their ids
     * @param ids Mechanics ids
     * @return A list of mechanics
     */
	List<MechanicDALDto> findByIds(List<String> ids);

}
