package com.bengkel.booking.repositories;

import java.util.Arrays;
import java.util.List;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;

public class ItemServiceRepository {

    List<ItemService> listItemService;

    private ItemServiceRepository() {
        initialServiceDataDummy();
    }

    public List<ItemService> getAll(){
        return listItemService;
    }

    private void initialServiceDataDummy() {

        if (listItemService == null) {

            ItemService itemService1 = new ItemService("Svcm-001", "Ganti Oli", "Motorcyle", 70000);
            ItemService itemService2 = new ItemService("Svcm-002", "Service Mesin", "Motorcyle", 150000);
            ItemService itemService3 = new ItemService("Svcm-003", "Service CVT", "Motorcyle", 50000);
            ItemService itemService4 = new ItemService("Svcm-004", "Ganti Ban", "Motorcyle", 200000);
            ItemService itemService5 = new ItemService("Svcm-005", "Ganti Rem", "Motorcyle", 90000);

            ItemService itemService6 = new ItemService("Svcc-001", "Ganti Oli", "Car", 170000);
            ItemService itemService7 = new ItemService("Svcc-002", "Service Mesin", "Car", 500000);
            ItemService itemService8 = new ItemService("Svcc-003", "Service AC", "Car", 250000);
            ItemService itemService9 = new ItemService("Svcc-004", "Isi Freon AC", "Car", 70000);
            ItemService itemService10 = new ItemService("Svcc-005", "Cuci Mobil", "Car", 50000);

            listItemService = Arrays.asList(itemService1, itemService2, itemService3, itemService4, itemService5, itemService6, itemService7, itemService8, itemService9, itemService10);

        }
    }


    private static ItemServiceRepository INSTANCE;

    public static ItemServiceRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemServiceRepository();
        }
        return INSTANCE;
    }
}
