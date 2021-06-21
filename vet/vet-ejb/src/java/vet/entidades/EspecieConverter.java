package vet.entidades;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Jean Lima
 */
//@Converter(autoApply = true)
public class EspecieConverter /*implements AttributeConverter<Especie, String>*/ {

    /*@Override
    public String convertToDatabaseColumn(Especie especie) {

        if (especie == null) {
            return null;
        }
        return especie.getEspecie();
    }

    @Override
    public Especie convertToEntityAttribute(String especie) {

        if (especie == null) {
            return null;
        }
        return Stream.of(Especie.values())
                .filter(e -> e.getEspecie().equals(especie))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }*/
}
