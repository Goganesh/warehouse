package com.goganesh.warehouse.domain;

import lombok.Data;
import java.util.List;

@Data
public class DataTablesPage<T> {

    private long draw;
    private long recordsTotal;
    private long recordsFiltered;
    private String value;

    private List<T> data;

}
