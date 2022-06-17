package com.talos.hospital.Util;

import com.talos.hospital.Model.Position;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PositionConverter implements AttributeConverter<Position, String> {

    @Override
    public String convertToDatabaseColumn(Position position) {
        if (position == null) {
            return null;
        }
        return position.getPosition();
    }

    @Override
    public Position convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Position.values())
                .filter(c -> c.getPosition().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
