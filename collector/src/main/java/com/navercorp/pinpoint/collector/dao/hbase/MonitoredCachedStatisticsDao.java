/*
 * Copyright 2021 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.collector.dao.hbase;

import com.navercorp.pinpoint.collector.dao.CachedStatisticsDao;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Taejin Koo
 */
public abstract class MonitoredCachedStatisticsDao implements CachedStatisticsDao {

    private final AtomicLong rejectedCount = new AtomicLong();
    private long flushCount;
    private long lastFlushTimeMillis;

    protected void reportFlushAll() {
        flushCount++;
        lastFlushTimeMillis = System.currentTimeMillis();
    }

    protected void reportReject() {
        rejectedCount.incrementAndGet();
    }

    public long getFlushAllCount() {
        return flushCount;
    }

    public long getRejectedCount() {
        return rejectedCount.get();
    }

    public long getLastFlushTimeMillis() {
        return lastFlushTimeMillis;
    }

}
