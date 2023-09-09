package com.rv.booking.ticket.mapper;

import com.rv.booking.ticket.entities.dto.DiscountDTO;
import com.rv.booking.ticket.entities.dto.PriceDTO;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-09T22:36:51+1000",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.8 (JetBrains s.r.o.)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public DiscountDTO toDiscountsDTO(Discounts discountsInfo) {
        if ( discountsInfo == null ) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setTicketType( discountsInfo.getTicketType() );
        discountDTO.setDiscount( discountsInfo.getDiscount() );

        return discountDTO;
    }

    @Override
    public List<DiscountDTO> toDiscountsDTOs(List<Discounts> educationList) {
        if ( educationList == null ) {
            return null;
        }

        List<DiscountDTO> list = new ArrayList<DiscountDTO>( educationList.size() );
        for ( Discounts discounts : educationList ) {
            list.add( toDiscountsDTO( discounts ) );
        }

        return list;
    }

    @Override
    public PriceDTO toPriceDTO(PriceDetails priceDetails) {
        if ( priceDetails == null ) {
            return null;
        }

        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setMovieType( priceDetails.getMovieType() );
        priceDTO.setPrice( priceDetails.getPrice() );

        return priceDTO;
    }

    @Override
    public List<PriceDTO> toPriceDTOs(List<PriceDetails> educationList) {
        if ( educationList == null ) {
            return null;
        }

        List<PriceDTO> list = new ArrayList<PriceDTO>( educationList.size() );
        for ( PriceDetails priceDetails : educationList ) {
            list.add( toPriceDTO( priceDetails ) );
        }

        return list;
    }
}
