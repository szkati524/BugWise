package com.example.bugwise.service;

import com.example.bugwise.dto.InsectOrderDTO;
import com.example.bugwise.entity.InsectOrder;
import com.example.bugwise.repository.InsectOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsectOrderService {
private final InsectOrderRepository insectOrderRepository;
@Autowired
    public InsectOrderService(InsectOrderRepository insectOrderRepository) {
        this.insectOrderRepository = insectOrderRepository;
    }

    public List<InsectOrderDTO> getAllInsectOrder(){
    return insectOrderRepository.findAll().stream().map(this::mapToDTO).toList();
    }
    public InsectOrderDTO findByIdInsectOrder(Long id) {
    return insectOrderRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new EntityNotFoundException("order with id " + id + " not found"));
    }
    @Transactional
    public InsectOrderDTO addInsectOrder(InsectOrder insectOrder){
    InsectOrder insectOrderSaved =  insectOrderRepository.save(insectOrder);
    return mapToDTO(insectOrderSaved);
    }
    @Transactional
    public void deleteInsectOrder(Long id){
    insectOrderRepository.deleteById(id);
    }
    @Transactional
    public InsectOrderDTO updateInsectOrder(Long id,InsectOrder orderDetails){
    InsectOrder order = insectOrderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("order with id " + id + " not found"));
    order.setName(orderDetails.getName());
    order.setLatinName(orderDetails.getLatinName());
    order.setDescription(orderDetails.getDescription());
    InsectOrder updatedInsectOrder = insectOrderRepository.save(order);
    return mapToDTO(updatedInsectOrder);
    }


    private InsectOrderDTO mapToDTO(InsectOrder insectOrder){
    return  new InsectOrderDTO(
            insectOrder.getId(),
            insectOrder.getName(),
            insectOrder.getLatinName(),
            insectOrder.getDescription()

    );
    }
}
