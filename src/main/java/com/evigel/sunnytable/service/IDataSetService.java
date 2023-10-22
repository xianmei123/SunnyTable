package com.evigel.sunnytable.service;

import com.evigel.sunnytable.dto.DataSetDto;

public interface IDataSetService {
    DataSetDto openDataset(long fid);
    void exportDataset(String uid, int type, DataSetDto data);
    long saveDataset(DataSetDto data);
    long replaceDataset(DataSetDto data);
}
