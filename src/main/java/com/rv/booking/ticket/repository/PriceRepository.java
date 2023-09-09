package com.rv.booking.ticket.repository;

import com.rv.booking.ticket.entities.model.PriceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceDetails, Long> {
    @Query("SELECT t FROM PriceDetails t WHERE LOWER(t.movieType) = LOWER(:movieType)")
    public PriceDetails findByMovieType(@Param("movieType") String movieType);

}