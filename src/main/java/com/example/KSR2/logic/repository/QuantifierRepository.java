package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.Quantifier;
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
public class QuantifierRepository {
    List<Quantifier> quantifiers = new ArrayList<>();

    public Quantifier getQuantifierByName(String name) {
        return quantifiers.stream().filter(x -> x.getLabel().getName().equals(name)).toList().get(0);
    }

    public boolean add(Quantifier quantifier) {
        return quantifiers.add(quantifier);
    }

    public boolean remove(Quantifier quantifier) {
        return quantifiers.remove(quantifier);
    }

    public Quantifier getQuantifierById(int id) {
        return quantifiers.get(id);
    }
}
