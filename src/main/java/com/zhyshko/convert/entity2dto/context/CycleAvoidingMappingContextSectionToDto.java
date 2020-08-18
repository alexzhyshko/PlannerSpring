package com.zhyshko.convert.entity2dto.context;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class CycleAvoidingMappingContextSectionToDto {
    private Map<com.zhyshko.model.Section, com.zhyshko.dto.Section> knownInstances = new IdentityHashMap<>();

    public com.zhyshko.dto.Section getMappedInstance(com.zhyshko.model.Section source, @TargetType Class<com.zhyshko.dto.Section> targetType) {
        return knownInstances.get(source);
    }

    public void storeMappedInstance(com.zhyshko.model.Section source, @MappingTarget com.zhyshko.dto.Section target) {
        knownInstances.put( source, target );
    }
    
    public int getInstanceCount() {
    	return this.knownInstances.size();
    }
    
    public List<com.zhyshko.dto.Section> getAllKnownInstances(){
    	return knownInstances.values().stream().collect(Collectors.toList());
    }
}