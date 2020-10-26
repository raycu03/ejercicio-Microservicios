package com.ejercicio.planificacion.shared;

import lombok.Getter;

@Getter
public class Queue {

    /*
     * QUEUES
     * */
    public final static String REGIONS = "regions";
    public final static String PACKAGE = "package";
    public final static String DELIVERIES = "deliveries";
    public final static String PACKAGE_DELIVERIES = "day";


    /*
     * ACTIONS
     * */
    public final static String SAVE_ACTION = "save";
    public final static String UPDATE_ACTION = "update";
    public final static String DELETE_ACTION = "delete";
}
