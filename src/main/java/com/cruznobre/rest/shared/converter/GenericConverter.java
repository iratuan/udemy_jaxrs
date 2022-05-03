package com.cruznobre.rest.shared.converter;

import org.apache.commons.lang3.Validate;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GenericConverter<T, E> {

    private ModelMapper modelMapper;
    private final Class<T> entity;
    private final Class<E> dto;

    public GenericConverter(Class<T> entity, Class<E> dto) {
        this.entity = entity;
        this.dto = dto;
        this.modelMapper = new ModelMapper();
    }

    public Class<T> toEntity(Class<E> dto){
        return modelMapper.map(dto,entity.getClass());
    }

    public Class<E> toDTO(Class<T> entity){
        return modelMapper.map(entity,dto.getClass());
    }


    public void retrievAllFields() {
        List<Field> fields = getAllFieldsList(entity);
        for (Field f : fields) {
            System.out.println("---------");
            System.out.println("nome " + f.getName());
            System.out.println("tipo " + f.getType());
        }

        // for (Field f : fields) {

        // System.out.println("Methods names: ");
        // String setMethodName = "set" + StringUtils.capitalize(f.getName());
        // String getMethodName = "get" + StringUtils.capitalize(f.getName());
        // System.out.println(setMethodName);
        // System.out.println(getMethodName);

        // try {
        // Method set = dto.getClass().getMethod(setMethodName);
        // Method get = entity.getClass().getMethod(getMethodName);
        // set.invoke(dto, get.invoke(entity));
        // System.out.println("=====>" + get.invoke(dto));
        // } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
        // IllegalArgumentException
        // | InvocationTargetException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // }
    }

    public List<Field> getAllFieldsList(final Class<T> cls) {
        Validate.isTrue(cls != null, "The class must not be null");
        List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            Collections.addAll(allFields, declaredFields);
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }

    public void retrieveAllMethods() {
        Method[] methods = entity.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("---------");
            System.out.println("nome " + m.getName());
            System.out.println("parametros " + m.getParameters().getClass());
        }
    }
}
