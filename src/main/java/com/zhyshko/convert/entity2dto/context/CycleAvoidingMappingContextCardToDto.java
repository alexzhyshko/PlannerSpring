package com.zhyshko.convert.entity2dto.context;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextCardToDto {
    private Map<com.zhyshko.model.Card, com.zhyshko.dto.Card> knownInstances = new IdentityHashMap<>();
    

    public com.zhyshko.dto.Card getMappedInstance(com.zhyshko.model.Card source, @TargetType Class<com.zhyshko.dto.Card> targetType) {
        return knownInstances.get(source);
    }

    public void storeMappedInstance(com.zhyshko.model.Card source, @MappingTarget com.zhyshko.dto.Card target) {
        knownInstances.put( source, target );
    }
    
    public int getInstanceCount() {
    	return this.knownInstances.size();
    }
    
    public List<com.zhyshko.dto.Card> getAllKnownInstances(){
    	return knownInstances.values().stream().collect(Collectors.toList());
    }
}