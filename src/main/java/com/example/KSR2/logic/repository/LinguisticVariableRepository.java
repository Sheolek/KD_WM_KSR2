package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.LinguisticVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Repository
public class LinguisticVariableRepository {
    List<LinguisticVariable> variables = new ArrayList<>();

    public LinguisticVariable getByName(String name) {
        return variables.stream().filter(x -> x.getName().equals(name)).toList().get(0);
    }

    public boolean add(LinguisticVariable variable) {
        return variables.add(variable);
    }

    public boolean remove(LinguisticVariable variable) {
        return variables.remove(variable);
    }

    public LinguisticVariable getLinguisticVariableById(int id) {
        return variables.get(id);
    }
}
