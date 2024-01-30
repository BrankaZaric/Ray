package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Banka;

public interface BankaRepository extends JpaRepository <Banka, Integer>{
	
	List<Banka> findByNazivContainingIgnoreCase(String naziv);
	
	@Query(value="select * from Banka where lower(naziv) like :pocetak%", nativeQuery = true)
	List<Banka> getBankaByPocetakNaziva(@Param("pocetak")String pocetakNaziva);
}
