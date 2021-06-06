package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.eMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;


    @Transactional
    public StockDTO save(StockDTO stockDTO) {

        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(stockDTO.getName(),stockDTO.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(eMessageException.STOCK_ALREADY_EXISTS.getMessage());
        }

        Stock stock = stockMapper.stockDTOToStock(stockDTO);
        stockRepository.save(stock);
        return stockMapper.stockToStockDTO(stock);
    }

    @Transactional
    public StockDTO update(StockDTO stockDTO) {
        Optional<Stock> optionalStock = stockRepository.findByStockUpdate(stockDTO.getName(),stockDTO.getDate(), stockDTO.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(eMessageException.STOCK_ALREADY_EXISTS.getMessage());
        }

        Stock stock = stockMapper.stockDTOToStock(stockDTO);
        stockRepository.save(stock);
        return stockMapper.stockToStockDTO(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockMapper.stocksToAllStockDTO(stockRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::stockToStockDTO).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO stockDTO = this.findById(id);
        stockRepository.deleteById(stockDTO.getId());
        return stockDTO;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return stockRepository.findByToday(LocalDate.now()).map(stockMapper::stocksToAllStockDTO).orElse(new ArrayList<>());
    }
}
