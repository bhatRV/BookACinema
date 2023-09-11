package com.rv.booking.ticket.mapper;

import com.rv.booking.ticket.entities.dto.DiscountDTO;
import com.rv.booking.ticket.entities.dto.PriceDTO;
import com.rv.booking.ticket.entities.model.Discounts;
import com.rv.booking.ticket.entities.model.PriceDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    DiscountDTO toDiscountsDTO(Discounts discountsInfo);

    List<DiscountDTO> toDiscountsDTOs(List<Discounts> educationList);

    @Mapping( source = "ticketType", target = "ticketType" )
    PriceDTO toPriceDTO(PriceDetails priceDetails);

    List<PriceDTO> toPriceDTOs(List<PriceDetails> educationList);

}