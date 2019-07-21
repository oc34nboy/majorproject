package com.m.wecare;

import android.content.Context;
import android.support.annotation.NonNull;

import com.m.wecare.source.MedicineRepository;
import com.m.wecare.source.local.MedicinesLocalDataSource;


public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}
