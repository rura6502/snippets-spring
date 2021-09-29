# Introduction

cache test

## CacheTest1#test_select_not_caching

you can see the log for executing query per each call.

In `CacheTestMapper.xml`, `flushCache="true"` option makes flushing cache every time.

## CacheTest1#test_select_cacing

you can see the log just first call and another query result is from cache.

In `CacheTestMapper.xml`, `<cache />` makes cache enable.