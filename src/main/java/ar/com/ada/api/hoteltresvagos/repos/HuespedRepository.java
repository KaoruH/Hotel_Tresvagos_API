package ar.com.ada.api.hoteltresvagos.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.hoteltresvagos.entities.Huesped;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {

    Huesped findByNombre(String nombreHuesped);

    Huesped findByDni(Integer dni);

    //@NamedQuery(name = "Huesped.findAllByNombreContiene", query = "FROM Huesped WHERE nombre like CONCAT('%', ?1,'%')")
    @Query("SELECT h FROM Huesped h WHERE h.nombre like CONCAT('%', ?1, '%')")
    List<Huesped> findAllByNombreContiene(String nombre);

    @Query("SELECT h FROM Huesped h WHERE h.nombre like CONCAT('%', ?1, '%') AND h.dni = ?2 ")
    List<Huesped> findAllByNombreAndDni(String nombre, Integer dni);

    @Query("SELECT h FROM Huesped h ORDER BY nombre")
    List<Huesped> findAllOrderByName();

    // Mismo caso anterior, salvo que en este se le pone "nombre" al paremtro. En
    // nuestro caso el nombre del parametro es ":dni"
    @Query("SELECT h FROM Huesped h WHERE h.dni = :dni")
    List<Huesped> findByDniVersion2(@Param("dni") Integer descripcion);
    
}