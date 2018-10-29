package com.wasp.landlordcommunication.repositories.base;

import java.util.List;

public interface CacheRepository<T> {
    void cacheData(List<T> items);

    List<T> getCachedData();
}
