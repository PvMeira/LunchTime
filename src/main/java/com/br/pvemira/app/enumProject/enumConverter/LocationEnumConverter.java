package com.br.pvemira.app.enumProject.enumConverter;

import com.br.pvemira.app.enumProject.LocationEnum;

import javax.persistence.AttributeConverter;

/**
 * Created by pvmeira on 17/06/17.
 */
public class LocationEnumConverter implements AttributeConverter<LocationEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(LocationEnum attribute) {
        return attribute.getId();
    }

    @Override
    public LocationEnum convertToEntityAttribute(Integer dbData) {
        return LocationEnum.findById(dbData);
    }
}
