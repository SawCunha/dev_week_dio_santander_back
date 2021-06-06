package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDTO stockToStockDTO(Stock stock);
    List<StockDTO> stocksToAllStockDTO(List<Stock> stock);
    Stock stockDTOToStock(StockDTO stockDTO);

}
